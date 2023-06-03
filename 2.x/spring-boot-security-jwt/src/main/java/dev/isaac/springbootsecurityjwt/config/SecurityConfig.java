package dev.isaac.springbootsecurityjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 기본적인 Web security active, Spring Security 설정 활성화
public class SecurityConfig {
    // spring boot 2.7.1에서 WebSecurityConfigurerAdopter 클래스는 deprecated 됨.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()    // HttpServletRequest 를 사용하는 요청들에 대한 접근제한을 설정함
                .antMatchers("/api/hello").permitAll()  // 인증없이 접근 허용
                .anyRequest().authenticated();  // 나머지 모든 요청은 인증을 받아야 한다.

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(
                "/h2-console/**"
                ,"/favicon.ico"
        );
    }

}
