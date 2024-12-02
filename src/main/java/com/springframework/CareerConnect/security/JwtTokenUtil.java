package com.springframework.CareerConnect.security;

import com.springframework.CareerConnect.domain.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public JwtTokenUtil() throws Exception {
        this.publicKey = loadPublicKey("src/main/resources/keys/public_key.pem");
        this.privateKey = loadPrivateKey("src/main/resources/keys/private_key.pem");
    }

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    public String generateAccessToken(User user) {
        return Jwts.builder().setSubject(String.format("%s, %s", user.getProfileId(), user.getEmail()))
                .setIssuer("CareerConnect")
                .setIssuedAt(new Date())
                .claim("roles", user.getRoles())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.RS512, privateKey)
                .compact();

    }

   public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();

   }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            logger.debug("JWT expired: {}" , ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.debug("Token is null, empty or has only whitespace: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.debug("JWT is invalid: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.debug("JWT is not supported: {}", ex.getMessage());
        } catch (SignatureException ex) {
            logger.debug("Signature validation failed: {}", ex.getMessage());
        }
        return false;
    }

    //Hiç emin değilim

    private RSAPublicKey loadPublicKey(String filePath) throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(filePath)))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    private RSAPrivateKey loadPrivateKey(String filePath) throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(filePath)))
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }
}
