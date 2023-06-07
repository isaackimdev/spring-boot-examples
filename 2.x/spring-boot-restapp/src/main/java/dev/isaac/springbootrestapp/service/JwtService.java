package dev.isaac.springbootrestapp.service;

public interface JwtService {
    String createToken(String subject, long ttlMillis);
    String getSubject(String token);
}
