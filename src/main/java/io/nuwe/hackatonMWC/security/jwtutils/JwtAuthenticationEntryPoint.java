package io.nuwe.hackatonMWC.security.jwtutils;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,
Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Having created the filter for our requests, we now create the
	 * JwtAutheticationEntryPoint class. This class extends Spring’s
	 * AuthenticationEntryPoint class and rejects every unauthenticated request with
	 * an error code 401 sent back to the client. We have overridden the commence()
	 * method of AuthenticationEntryPoint class to do that.
	 */
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}