package neki.com.Backend.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	private final SecretKey chave;
	private final long expiracao;

	public TokenService(@Value("${app.jwt.secret}") String secret,
			@Value("${app.jwt.expiration}") long expiracao) {
		this.chave = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
		this.expiracao = expiracao;
	}

	public String gerarToken(Long adminId) {
		Instant agora = Instant.now();
		Instant expiraEm = agora.plus(expiracao, ChronoUnit.MILLIS);

		return Jwts.builder()
				.subject(adminId.toString())
				.issuedAt(Date.from(agora))
				.expiration(Date.from(expiraEm))
				.signWith(chave)
				.compact();
	}

	public Long extrairAdminId(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(chave)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return Long.valueOf(claims.getSubject());
	}
}