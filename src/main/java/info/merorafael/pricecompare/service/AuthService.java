package info.merorafael.pricecompare.service;

import info.merorafael.pricecompare.data.response.AccessToken;
import info.merorafael.pricecompare.entity.User;
import info.merorafael.pricecompare.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthService {
    protected final SecretKey secretKey;
    protected final UserRepository userRepository;

    public AuthService(@Value("${project.jwt.secret}") String secretKey, UserRepository userRepository) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.userRepository = userRepository;
    }

    public User getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
            return null;
        }

        return (User) authentication.getPrincipal();
    }

    public User getUser(String token) throws UsernameNotFoundException {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return userRepository.findByEmail(claims.getSubject())
                    .orElseThrow(() -> new UsernameNotFoundException(String.format(
                            "E-mail '%s' does not exist",
                            claims.getSubject()
                    )));
        } catch (JwtException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    public AccessToken createToken(User user) {
        var now = Instant.now();
        var expirationDate = Date.from(now.plus(30L, ChronoUnit.MINUTES));
        var token = Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setExpiration(expirationDate)
                .setSubject(user.getEmail())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return new AccessToken(
            user.getEmail(),
            token,
            expirationDate
        );
    }
}
