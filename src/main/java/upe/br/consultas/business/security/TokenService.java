package upe.br.consultas.business.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import upe.br.consultas.infra.entities.Recepcionista;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    String segredoToken;

    public String generateToken(Recepcionista recepicionista) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredoToken);

            return JWT.create()
                    .withIssuer("integracao-consultas")
                    .withSubject(recepicionista.getEmail())
                    .withExpiresAt(
                            LocalDateTime.now()
                                    .plusHours(5)
                                    .toInstant(ZoneOffset.of("-03:00"))
                    )
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Ops! Erro ao gerar o token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredoToken);

            return JWT.require(algorithm)
                    .withIssuer("arthub")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extractEmailFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(segredoToken);

        DecodedJWT jwt = JWT.require(algorithm)
                .withIssuer("arthub")
                .build()
                .verify(token);

        return jwt.getSubject();
    }
}
