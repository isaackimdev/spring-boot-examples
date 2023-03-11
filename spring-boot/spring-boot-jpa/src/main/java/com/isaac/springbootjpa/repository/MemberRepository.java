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
     * like search
     * */
    // Search Member
    Page<Member> findByNameContaining(String name, Pageable pageable);
    Page<Member> findByIdContaining(String id, Pageable pageable);
    // like search
    Page<Member> findByPhoneContaining(String phone, Pageable pageable);


}
