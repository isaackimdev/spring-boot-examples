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

## 동시성
1. 동시성
   - 여러 서버가 동시에 어떤 상품의 재고 수량을 줄인다고 했을 때의 이슈
2. 동시성 해결 방법
   1. 데이터베이스 Lock 활용하기
      - Pessimistic Lock
        - Transaction 에 Lock 을 건다. 처리가 다 끝나지 않으면 다음 트랜잭션에서는 대기 상태
      - Optimistic Lock
        - Transaction 을 version 정보로 던져서 관리한다. 동시 작업에서 최종 버전을 확인하고 일치하지 않으면 에러
   2. Redis 이용하기
      - Lettuce
        1. setnx 명령어 활용하여 분산락 구현
          - 기존 값이 없을 때만 set
        2. setnx 는 Spin lock 방식으로 retry 로직을 개발자가 작성
          - lock 을 획득할 수 있을지 반복적으로 확인하면서 시도
      - Redisson
        1.  Pub-sub 기반의 Lock 구현을 제공
          - 채널을 통해 Lock 해제를 대기중인 스레드에 알려주는 방식
        2. 별도의 Retry 방식 작성할 필요 X
        3. 별도의 라이브러리를 사용해야 함.
   3. 쿼리 직접 실행하기 (많이 썼다고 함.) 
      1. 직접 update 쿼리 실행하기

## 스프링 학습 로드맵
1. Java + 디자인패턴
2. 스프링 핵심 개념 (DI, IoC, AOP)
3. 스프링 코어
4. 스프링 MVC
5. 스프링 데이터 액세스 (Spring data JPA)
6. 스프링 시큐리티 (Login, 보안 등)
7. 스프링 배치
8. 실전 프로젝트

## Spring 더 나아가기 
1. 스프링 공식 문서 및 튜토리얼 학습
   1. 스프링 공식 문서에는 다양한 모듈과 기능에 대한 상세한 설명 제공
   2. 예제 코드 + 튜토리얼 포하
   3. 이를 통해 스프링의 기본 개념과 사용법 학습
2. 스프링 관련 오픈소스 프로젝트 참여
   1. 스프링은 수많은 오픈소스 프로젝트가 존재
   2. 해당 오픈소스 프로젝트에 기여하거나 활동
   3. 스프링 활용 능력을 키울 수 있는 좋은 방법
   4. 개발 참여를 통한 기여
   5. 버그 수정, 문서 작성 등을 통한 기여
3. 스프링 커뮤니티 참여
   1. 스프링은 활발하게 개발자 커뮤니티 운영
   2. 온라인 포럼, 개발자 컨퍼런스 등 참여
   3. 다른 스프링 개발자와 네트워킹을 하며 실무 역량↑
4. 스프링 개인 프로젝트
   1. 학습한 개념을 바탕으로 개인프로젝트 진행
   2. 실무에서 사용 예정인 내용을 미리 프로젝트를 통해 연습
   3. 언제나 활용가능한 스켈레톤 프로젝트

## Spring Project 투입 시뮬레이션
1. 개발 된 스프링 프로젝트를 분석하는 방법
   1. repository 이름과 README 파일을 보고 프로젝트 파악
      1. 대부분의 회사들이 git을 통해 프로젝트 관리
      2. repository 이름과 README 파일을 보며 어떤 프로젝트인지 파악
   2. 패키지 구조 파악
      1. 패키지 구조를 통해 프로젝트가 어떻게 설계되었는지 파악
      2. 하나의 프로젝트는 여러 모듈로 구성되기도 함
      3. 이 역시 패키지 구조를 통해 전체적인 구조 파악 가능
   3. 빌드 설정 파일 확인
      1. 빌드 설정 파일을 통해 주입된 라이브러리 확인
      2. 이를 통해 어떤 기능이 개발되어 있는지 대략적인 유추 가능
   4. config 패키지 내부 소스 확인
      1. 실무의 프로젝트는 config를 분리하여 관리
      2. config 코드를 보면 어떤 설정이 적용되어 있는지 확인 가능 
   5. controller 패키지 내부 소스 확인
      1. 프로젝트에서 처리하는 요청과 응답을 분석
      2. 제공되는 api 스펙문서와 함께 분석
   6. service 패키지 내부 소스 확인
      1. service 내에 구현된 메서드 분석
      2. 이를 통해 전체적인 비즈니스 로직 파악
   7. resource 하위 파일 분석
      1. application 설정 파일
      2. db 설정 파일
   8. Test 코드 분석 ★★
      1. 구현된 테스트 코드를 통해 비즈니스 로직 확인 (TDD)
      2. 해피 패스, 예외 케이스 모두 확인





---
- [Spring Quickstart Guide](https://spring.io/quickstart)
- [rest-service](https://spring.io/guides/gs/rest-service)
- [mvn repository](https://mvnrepository.com/)
- [multicampus spring ex](https://github.com/spring-kang/spring-basic)