package dev.isaac.springbootjwt.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final String secretKey = "jwt12345";

    /**
     * @param subject 토큰에 넣을 내용
     * @param ttlMillis 토큰 만료 기간
     * */
    @Override
    public String createToken(String subject, long ttlMillis) {

        if (ttlMillis <= 0) {
            throw new RuntimeException("The token validity time is less than or equal to zero.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setSubject(subject).signWith(signatureAlgorithm, signingKey);

        long nowMillis = System.currentTimeMillis();
        builder.setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }

    @Override
    public String getSubject(String token) {
        return null;
    }
}
