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


   
---
- [Spring Quickstart Guide](https://spring.io/quickstart)
- [rest-service](https://spring.io/guides/gs/rest-service)
- [mvn repository](https://mvnrepository.com/)
- [multicampus spring ex](https://github.com/spring-kang/spring-basic)