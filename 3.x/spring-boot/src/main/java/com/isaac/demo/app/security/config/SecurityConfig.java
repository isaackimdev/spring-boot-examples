package com.isaac.demo.app.security.config;

import com.isaac.demo.app.security.filter.JwtAuthenticationFilter;
import com.isaac.demo.app.security.token.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;

    // DIP
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                auth -> auth.requestMatchers(AntPathRequestMatcher
                        .antMatcher("/h2-console"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/login"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/sign"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**"))
                        .hasRole("USER")
                        .anyRequest()
                        .denyAll())
                .headers(header -> header.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
                .build();
    }
}
