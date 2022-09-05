package it.epicode.be.library.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.epicode.be.library.security.service.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	// chiave segreta per firmare i token
	@Value("${jwt.jwtSecret}")
	private String jwtSecret;

	// validità del token in ms
	@Value("${jwt.jwtExpirationMs}")
	private Long jwtExpirationMs;

	/**
	 * Genera un token JWT impostando - data di creazione - data di scadenza -
	 * username utente loggato e lo firma con la chiave segreta e l'algoritmo HS512
	 * 
	 * @param authentication informazioni relative all'utente
	 * @return stringa corrispondente al token
	 */
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Date now = new Date();
		Date exp = new Date((now).getTime() + jwtExpirationMs);
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(now).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Estrae lo username dal token
	 * 
	 * @param token JWT
	 * @return username
	 */
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Effettua la validazione del token 
	 * 
	 * @param authToken toke JWT
	 * @return true se il token è valido, false altrimenti
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
