package io.nuwe.hackatonMWC.security.jwtutils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component 
public class TokenManager implements Serializable {
	/**
	 * This class will be responsible for the creation and validation of tokens
	 * using io.jsonwebtoken.Jwts.
	 */ 
	
   private static final long serialVersionUID = 7008375124389347049L;
   
	/**
	 * Here, as all tokens should have an expiration date, we start with a token
	 * validity constant. Here, we want our token to be valid for 10 minutes after
	 * generation. We will use this value when we generate our token. 
	 */
   public static final long TOKEN_VALIDITY = 10 * 60 * 60; 
   
	/**
	 * We extract the value of our singing key from the application.properties file
	 * into our jwtSecret field using the @Value annotation.
	 */
   @Value("${secret}") 
   private String jwtSecret; 

	/**
	 * This method is used to generate a token on successful authentication by the
	 * user. To create the token here we use the username, issue date of token and
	 * the expiration date of the token. This will form the payload part of the
	 * token or claims as we had discussed earlier. To generate the token we use the
	 * builder() method of Jwts. This method returns a new JwtBuilder instance that
	 * can be used to create compact JWT serialized strings.
	 */
   public String generateJwtToken(UserDetails userDetails) { 
      Map<String, Object> claims = new HashMap<>();
		/**
		 * To set the claims we use the setClaims() method and then set each of the
		 * claims. For this token we have setSubject(username), issue date and
		 * expiration date. We can also put our custom claims as we had discussed above.
		 * This can be any value we want which might include user role, user authorities
		 * and so on.
		 * 
		 * Then we set the signature part of the token. This is done using the
		 * signWith() method, we set the hashing algorithm we prefer to use and the
		 * secret key. Then we use thecompact() method that builds the JWT and
		 * serializes it to a compact, URL-safe string according to the JWT Compact
		 * Serialization rules.
		 */
      return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()) 
         .setIssuedAt(new Date(System.currentTimeMillis())) 
         .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) 
         .signWith(SignatureAlgorithm.HS512, jwtSecret).compact(); 
   }
   
   
	/**
	 * To validate the token means to verify the request is an authenticated one and
	 * that the token is the one that was generated and sent to the user. Here, we
	 * need to parse the token for the claims such as username, roles, authorities,
	 * validity period etc.
	 */
   public Boolean validateJwtToken(String token, UserDetails userDetails) { 
      String username = getUsernameFromToken(token);
      
		/**
		 * To validate the token we need to parse it first. This is done using the
		 * parser() method of Jwts. We then need to set the signing key that we used to
		 * generate the token and then use parseClaimsJws() method on the token to parse
		 * the compact serialized JWS string based on the builder’s current
		 * configuration state and return the resulting Claims JWS instance. The
		 * getBody() method is then used to return the claims instance that was used
		 * while generating the token.
		 * 
		 * From this obtained claims instance, we extract the subject and the expiry
		 * date to verify the validity of the token. The username should be the username
		 * of the user and the token should not be expired. If both these conditions are
		 * met, we return true, which signifies that the token is valid.
		 */
      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
      return (username.equals(userDetails.getUsername()) && !isTokenExpired); 
   } 
   
   public String getUsernameFromToken(String token) {
      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); 
      return claims.getSubject(); 
   } 
}
