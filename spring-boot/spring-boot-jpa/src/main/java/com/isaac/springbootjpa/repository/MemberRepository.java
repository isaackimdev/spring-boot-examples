package com.isaac.springbootjpa.repository;

import com.isaac.springbootjpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// CrudRepository 또는 JpaRepository 상속.
// Entity 필요
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /**
     * = search
     * */
    Page<Member> findByName(String name, Pageable pageable);
    Page<Member> findById(String id, Pageable pageable);
    // like search
    Page<Member> findByPhone(String phone, Pageable pageable);


    /**
     * like search - % searchKeyword %
     * */
    // Search Member
    Page<Member> findByNameContaining(String name, Pageable pageable);
    Page<Member> findByIdContaining(String id, Pageable pageable);
    // like search
    Page<Member> findByPhoneContaining(String phone, Pageable pageable);

    /**
     * like search 2 - saerchKeyword + %
     * */
    Page<Member> findByPhoneLike(String phone, Pageable pageable);

    // 특정 문자열로 시작하는 단어를 찾는다고 할 때 사용 -> 와일드카드를 사용하지 않는다고 할 때
    Page<Member> findByPhoneStartsWith(String phone, Pageable pageable);

    // 특정 문자열로 종료하는 단어를 찾는다고 할 떄 사용 -> 와일드카드를 사용하지 않는다고 할 때
    Page<Member> findByPhoneEndsWith(String phone, Pageable pageable);

    /**
     * 대소문자 구분
     * 기본적으로 MySQL varchar 타입은 대소문자를 구분하지 않는다. 하지만 경우에 따라서 필요하다면?
     * 여러 방법 중 MySQL Column 속성을 varchar -> varbinary 속성으로 변경하여 사용.
     * 커맨드 모드에서 쿼리로 실습할 경우 [ where binary id = '' ] 로 검색할 수가 있음.
     */
    Page<Member> findByIdContains(String id, Pageable pageable);


    /**
     * and, or 쿼리 조건
     */
    Page<Member> findByNameAndId(String name, String id, Pageable pageable);
    Page<Member> findByNameContainsOrIdContains(String name, String id, Pageable pageable);

    /**
     * Sort
     * (1) 조건, (2) 정렬
     * */
    // [1] : 페이징이 필요 없을 시 -> List<Member> ~
    // List<Member> findByName(String name, Sort sort)

    // [2] : 페이징 필요 시 -> Page<Member> ~
    // 1. 회원 등록순 - num 오름차순 : Order By Num Asc
    // 2. 특정 회원번호(10번) 이상의 회원들만 검색
    // GreaterThan : >=
    // findByNumGreaterThanEqualOrderByNumAsc( parameter )
    Page<Member> findByNumGreaterThanEqualOrderByNumAsc(Integer num, Pageable pageable);
    Page<Member> findByNumGreaterThanEqualOrderByNameDesc(Integer num, Pageable pageable);


    // @Query Annotation 이용하여 쿼리 작성하는 방법
    // 사용 이유 : 보다 구체적이고 좀 더 직관적, 빠르고, 유연한 쿼리 작성하여 사용하고자 할 때 사용한다.
    // 이때 사용하는 쿼리는 -> JPQL 이라는 미니 SQL 사용. 또는 순수 SQL 사용.
    // JPQL : Java Persistence Query Language -> Table 아닌 Entity 대상으로 검색하는 객체지향 쿼리.
    // SQL : Table 대상

    // [0] 기본 사용법
    // @Query( JPQL 쿼리 작성 )
    // ? -> JDBC -> PreparedStatement 에서 사용하는 것과 동일.
    // ?1 -> 첫 번째 파라미터 값으로 전달
    // 메서드명은 고민할 필요 없음. -> asdf로 해도 된다.
    // 메서드 호출 시 제일 먼저 @Query 애너테이션의 JPQL 쿼리를 찾는다.

    // [1] : 회원 이름으로 검색하기 -> @Query
    // @Query( "SELECT m FROM Member m WHERE m.name = ?1 ORDER BY m.num DESC" ) // 세미콜론(;) 넣으면 오류 남.

    @Query( "SELECT m FROM Member m WHERE m.name LIKE %?1% ORDER BY m.name ASC ")
    Page<Member> findByNameQuery1(String name, Pageable pageable);


    // between example
    Page<Member> findByNameContainsAndNumBetweenOrderByName(String name, int start, int end, Pageable pageable);


}
