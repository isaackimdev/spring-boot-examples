package dev.isaac.springbootrestapp.advice;

import dev.isaac.springbootrestapp.annotation.TokenRequired;
import dev.isaac.springbootrestapp.service.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;


@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(tokenRequired)")
    public void authWithJwt(TokenRequired tokenRequired) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String token = request.getParameter("token");
        System.out.println("token : " + token);

        // checks for token in request header
//        String tokenInHeader = request.getHeader("token");
//        System.out.println("tokenInHeader : " + tokenInHeader);

        if (token.isEmpty()) {
            throw new IllegalArgumentException("Empty token");
        }
        Claims claims = Jwts.parser().setSigningKey(
                DatatypeConverter.parseBase64Binary(
                        JwtServiceImpl.secretKey
                )
        ).parseClaimsJws(token).getBody();
        if(claims == null || claims.getSubject() == null) {
            throw new IllegalArgumentException("Token error - Claim is null");
        }
        System.out.println("token subject 로 자체 인증처리 필요");
        System.out.println("subject : " + claims.getSubject());
    }

}
