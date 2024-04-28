# Spring boot 3.2.4 
## project example

1. 프로젝트 생성
2. Spring boot 3.2.4 version에 필요한 최소 java 버전 확인
   - [Spring boot 3.2.x version IntelliJ에서 실행 오류 해결](https://lifere.tistory.com/entry/Spring-boot-32x-version-IntelliJ%EC%97%90%EC%84%9C-%EC%8B%A4%ED%96%89-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0)
3. src/main/resources/application.yml
   - application.properties 파일에서 yml 확장자로 변경
   - server.port=8081 설정
4. build.gradle > dependencies 설정 확인
5. Controller 생성 후 Start
6. Test code 추가
7. AOP 추가
8. Commerce Project 로 Development

---
## 스프링 프로젝트 구성하기
1. 프로젝트 비즈니스 로직 / 필요 기능 정의 / 커머스 어플리케이션 기능 정의
   1. 회원 : 회원 등록, 회원 정보 수정, 회원 정보 조회, 로그인 기능 구현
   2. 상품 : 상품 등록, 상품 목록 조회, 상품 상세 정보 조회 기능 구현
   3. 주문 : 상품 주문, 주문 내역 조회, 주문 취소 기능 구현

2. 프로젝트 아키텍처
   1. 엔티티 설계
      - User Entity (회원 엔티티)
        1. 회원 정보를 저장하는 엔티티
        2. 이름, 주소, 이메일, 연락처 등 정보 포함
        3. 추후 로그인 서비스 연동 (스프링 시큐리티 활용)

      - Product Entity
        1. 상품 정보
        2. 이름, 가격, 설명, 재고량 포함
        3. 추후 재고량에 대한 동시성 처리 진행

      - Order Entity
        1. 주문 정보
        2. 주문번호, 주문일자, 주문한 상품 정보, 수량 등 포함

      - OrderItem Entity
        1. 주문과 상품 간 다대다 관계를 매핑하기 위해 사용하는 엔티티

   2. 관계 설정
      - User 와 Order 1:다
      - 즉, 한 명의 회원이 여러 개의 주문을 가질 수 있음

      - Product 와 Order 다:다
      - 즉, 한 개의 주문에는 여러 개의 상품이 포함될 수 있고,
      - 한 개의 상품이 여러 개의 주문에 포함될 수 있음
      - OrderItem 엔티티를 이용해 다대다 관계 매핑

   3. Entity 다이어그램
      - 설계
      - User(1) - (다)Order(다) - (다)Product(다)
      - 실제 사용 설계
      - User(1) - (다)Order(1) - (다)OrderItem(다) - (1)Product(다)

   3. 계층 설계

      1. Repository 인터페이스
         - UserRepository
         - ProductRepository
         - OrderRepository

      2. Service 계층
         - UserService
         - ProductService
         - OrderService

      3. Controller 계층
         - UserController
         - ProductController
         - OrderController

### 회원 도메인 설계
1. 회원 레포티토리 설계
   - User 엔티티를 다루기 위한 레포지토리 인터페이스
   - Spring Data JPA 활용하여 구현
2. 회원 서비스 설계
   - User 서비스 구현 (회원 관련 비즈니스 로직 처리)
   - user Repository와 연계
3. 회원 컨트롤러 설계
   - User 컨트롤러 생성
   - 회원 등록, 회원 정보 수정, 회원 정보 조회와 관련된 엔드포인트 제공
     - API 방식 : 클라이언트 요청을 받고 처리 후 응답하는 형태
     - SSR 방식 : 모델 객체를 뷰에 전달하여, 뷰에서 화면에 노출 시키는 형태
4. API 설계
   1. 회원 등록 : POST /api/users
   2. 회원 정보 수정 : PUT or PATCH /api/users/{id}
   3. 회원 정보 조회 : GET /api/users{id}
   4. 회원 정보 삭제 : DELETE /api/users/{id}

### 상품 도메인 설계
1. 상품 엔티티 설계
   - 상품 정보를 저장하는 엔티티
   - 상품명, 상품 가격, 상품 설명, 재고량, 카테고리 등 정보 포함
2. 상품 레포지토리 설계
   - Product 엔티티를 다루기 위한 레포지토리 인터페이스 생성
   - Spring Data JPA 활용하여 구현
3. 상품 서비스 설계
   1. Product 서비스 구현 (상품 관련 비즈니스 로직 처리)
      - 상품 생성 기능
      - 상품 수정 기능
      - 상품 상세정보 조회 기능
      - 상품 전체 조회 기능 등 제공
   2. Product 레포지토리와 연계
4. 상품 컨트롤러 설계
   1. Product 컨트롤러 생성
   2. 상품 생성, 수정, 상품 상세 조회, 상품 전체 조회와 관련된 엔드포인트 제공
5. API 설계
   1. 상품 생성 : POST /api/products
   2. 상품 수정 : PUT /api/products/{id}
   3. 상품 상세 조회 : GET /api/products/{id}
   4. 상품 전체 조회 : GET /api/products

### 주문 도메인 설계
1. 주문 엔티티 설계
   1. 주문 정보를 저장하는 엔티티
   2. 주문 일자, 주문 고객, 배송지, 주문 상태, 주문 상품 등 포함
2. 주문 레포지토리 설계
   1. Order 엔티티를 다루기 위한 레포지토리 인터페이스 생성
   2. Spring Data JPA 활용하여 구현
3. 주문 서비스 설계
   1. Order 서비스 구현 (주문 관련 비즈니스 로직 처리)
      - 주문 생성 기능
      - 주문 취소 기능
      - 주문 상세정보 조회 기능
   2. OrderRepository, UserRepository, ProductRepository 연계
4. 주문 컨트롤러 설계
   1. Order 컨트롤러 생성
   2. 주문 생성, 주문 취소, 주문 상세 정보 조회 등과 관련된 엔드포인트 제공
5. API 설계
   1. 주문 생성 : POST /api/orders
      - POST 요청에서는 request 바디에 데이터를 넣어 호출하도록 한다.
   2. 주문 취소 : PUT /api/orders/{id}/cancel
   3. 주문 상세 정보 조회 : GET /api/orders/{id}

- users, products, orders 이러한 복수형 표현을 하는 것이 RestAPI 설계의 기초적인 규칙이다.

## 로그인 연동
1. 스프링 시큐리티
2. 로그인 방식 비교
3. 로그인 연동하기


### 1. 스프링 시큐리티
- 웹 어플리케이션의 보안 기능을 제공하는 스프링 프레임워크
- 커스터마이징이 가능하고 확장성 높음
- 외부 공격에 대한 대응과 보안 정책의 일관된 적용 가능

1. 스프링 시큐리티 주요 기능
   1. 인증 (Authentication)
      - 사용자의 __신원__ 확인
      - 다양한 인증방식 지원, 기본적으로 아이디, 비밀번호를 사용한 폼 기반 인증
      - 다양한 인증 Provider를 제공하여 인증 처리 (DB, LDAP, OAuth 등)
   2. 인가 (Authorization)
      - 인증된 사용자에 대한 __권한__ 을 확인하여 접근 제어 수행
      - 어노테이션 기반의 접근 제어
      - 메서드 레벨이나 URL 패턴에 따라 설정
   3. 세션 관리
   4. 보안 헤더 (Security Headers) 처리
      - 보안 헤더를 통해 웹어플리케이션의 보안수준을 높임
      - X-Frame-Options, X-XSS-Protection 등의 헤더 설정

2. 로그인 방식 비교
   1. Session 방식
      1. 유저가 로그인하면, Session 을 생성하고 고유한 Session ID를 부여
      2. 서비스를 요청 받았을 때, Session ID를 기반으로 Session 값을 읽어 유저 인증
      3. 서버에서 Session 데이터를 저장하고 관리 -> 서버의 자원 필요
      4. 분산 환경에서는 Session 에 대한 관리가 필요
 
   __Session 생성__
   ```
   1. 클라이언트 -> (로그인 요청) -> 서버
   2. 서버 -> (Session 생성 및 유지) -> Session -> (응답) -> 클라이언트 
   ```
   __Seesion 사용__
   ```
   1. 클라이언트 -> (서비스 요청) -> 서버
   2. 서버 -> (Session 읽기) -> Session -> (응답) -> 클라이언트
   ```
   
   2. Token 방식
      1. 사용자가 로그인 하면, 서버에서 Token을 발급
      2. 클라이언트는 Token을 로컬 스토리지나 쿠키 등에 저장
      3. API 요청 시, Token을 함께 전송하여 서버에 인증 정보 제공
      4. 서버는 해당 Token에 대한 인증상태 확인
      5. 서버 자원 사용 X
      6. 분산환경이나 Micro Service, 모바일 환경에서 적절
   
   __Token 방식 : Token 생성 및 저장__
   ```
   1. 클라이언트 -> 로그인 요청 -> 서버
   2. 서버 -> Token 생성 -> 응답 -> 클라이언트(Token) -> Token 저장
   ```
   
   __Token 방식 : Token 검증__
   ```
   1. 클라이언트 -> 서비스 요청 / Token 전달 -> 서버(Token)
   2. 서버(Token) -> Token 검증 -> 응답 -> 클라이언트
   ```

3. 로그인 연동하기
   1. JWT를 활용한 로그인 연동 구현
      1. 사용자 인증 설정
         - UserDetailService 구현
         - SecurityConfig 생성
      2. 로그인 API 연동
         - 로그인 API 추가
      3. 토큰 검증
         - JwtProvider
      4. 기존 구현된 API에 적용


## Application 추상화
1. 추상화
   1. 세부 사항을 숨기고 핵심 개념이나 기능에 집중
   2. 복잡성을 줄이고 코드 이해도 향상
   3. 클래스와 객체를 이용하여 추상화 구현
   4. 추상 클래스, 인터페이스
   5. 객체지향 프로그래밍의 핵심 중 하나
2. Service 계층 추상화
   1. 개발된 서비스 계층 추상화
   2. 컨트롤러에서는 인터페이스를 통해 서비스 접근
   3. 다양한 구현체를 사용할 수 있는 유연성 제공
   4. 테스트 용이
   5. 코드의 재사용성 증가
   6. 유지보수 용이 
3. 할인 정책 적용
   1. 할인 정책 인터페이스를 생성 (추상화)
   2. 고정 할인 정책, 비율 할인 정책 구현체 개발
   3. 할인 정책이 바뀌어도, 신경 쓸 것 x


   
---
- [Spring Quickstart Guide](https://spring.io/quickstart)
- [rest-service](https://spring.io/guides/gs/rest-service)
- [mvn repository](https://mvnrepository.com/)
- [multicampus spring ex](https://github.com/spring-kang/spring-basic)