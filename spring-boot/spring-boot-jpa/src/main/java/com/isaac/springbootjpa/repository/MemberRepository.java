package com.isaac.springbootjpa.repository;

import com.isaac.springbootjpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
