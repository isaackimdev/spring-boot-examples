# Spring boot security & jwt

현, 미완성 프로젝트

spring boot security & jwt 를 사용한 example project 입니다.

project init config
![start](./image/start.png)

h2-console 페이지 접속 : http://localhost:8080/h2-console

h2-console 접속 방법
![img.png](./image/img.png)

to do list
- [x] entity set
- [x] Spring Boot Security 기본 설정
- [x] data settings
- [x] jwt dependencies 추가
- [x] jwt 관련 코드 개발
  - [x] TokenProvider
  - [x] JwtFilter
  - [x] JwtSecurityConfig
  - [x] JwtAuthenticationEntryPoint
  - [x] JwtAccessDeniedHandler
- [x] Security + jwt 적용 (SecurityConfig에서 jwt 설정 적용)
- Test... token 발급이 제대로 실행되지 않고 있음.