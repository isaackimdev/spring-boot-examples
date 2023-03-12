package com.isaac.springbootjpa;

import com.isaac.springbootjpa.entity.Member;
import com.isaac.springbootjpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class SpringBootJpaApplicationTests {

	@Autowired
	MemberRepository memberRepository;

	// Condition, Sort
	@Test
	void contextLoads() {
		Pageable pageable = PageRequest.of(0, 5);	// Pageable Test
		Page<Member> members = memberRepository.findByNumGreaterThanEqualOrderByNumAsc(6, pageable);
		members.stream().forEach(member -> {
			System.out.println(member.toString());
		});
		System.out.println("----------");
		List<Member> list = memberRepository.findAll();
		list.stream().forEach(member -> {
			System.out.println(member.toString());
		});
	}

	@Test
	void findByNumGreaterThanEqualOrderByNameDescTest() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Member> members = memberRepository.findByNumGreaterThanEqualOrderByNameDesc(2, pageable);
		members.stream().forEach(member -> {
			System.out.println(member.toString());
		});
	}
}
