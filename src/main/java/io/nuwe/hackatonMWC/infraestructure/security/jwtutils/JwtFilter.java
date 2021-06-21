package io.nuwe.hackatonMWC.infraestructure.security.jwtutils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.nuwe.hackatonMWC.infraestructure.security.service.JwtUserDetailsService;


@Component
public class JwtFilter extends OncePerRequestFilter {
	/**
	 * The filter class will be used to track our requests and detect if they
	 * contain the valid token in the header. If the token is valid we let the
	 * request proceed otherwise we send a 401 error (Unauthorized).
	 */
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private TokenManager tokenManager;

	/**
	 * As we can see above, we have autowired the JwtUserDetailsService and
	 * TokenManager classes here as well. We have extended the OncePerRequestFilter
	 * of SpringSecurity which makes sure the filter is run for every request. We
	 * have provided our implementation to the overridden method doFilterInternal()
	 * of the OncePerRequestFilter class.
	 */

	/**
	 * The method here extracts the token from the header and validates it with the
	 * help of validateJwtToken() method of our TokenManager class. During
	 * validation, it checks for the username and the expiration date. If both the
	 * values are valid, we save the authentication in our Spring Security context
	 * and let the code proceed to the next filter in our filter chain. If any of
	 * the validation fails or there is an issue with the token or if the token is
	 * not found we throw the appropriate exceptions and send back an appropriate
	 * response while blocking the request from moving ahead.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			token = tokenHeader.substring(7);
			try {
				username = tokenManager.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			System.out.println("Bearer String not found in token");
		}
		
		if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if (tokenManager.validateJwtToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
