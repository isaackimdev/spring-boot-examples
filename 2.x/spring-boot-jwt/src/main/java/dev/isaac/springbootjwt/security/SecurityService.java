package dev.isaac.springbootjwt.security;

public interface SecurityService {
    String createToken(String subject, long ttlMillis);
    String getSubject(String token);
}
