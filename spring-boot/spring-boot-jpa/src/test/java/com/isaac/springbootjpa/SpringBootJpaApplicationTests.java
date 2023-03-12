package com.isaac.springbootjpa;

import com.isaac.springbootjpa.entity.Member;
import com.isaac.springbootjpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

	@Test
	void pageRequestStudy() {
		// PageRequest.of() method 사용법
		// 1. Page 타입을 사용하여 페이징 구현 시 여러 유용한 기능들을 제공 -> PageRequest.of() 메서드 또한 매우 유용.
		// 2. PageRequest.of() 이용하면 --> 여러 설정 값들을 파라미터로 전달하여 페이징에 필요한 설정을 하는 것이 가능.
		// 3. SpringBoot 2.0 버전으로 넘어오면서 PageRequest.of()를 사용.
		// 4. 크게 4가지 파라미터 값을 기본적으로 암기 --> PageRequest.of( page, size, direction, column );
		// 5. Usage
		// 	5-1. PageRequest.of ( int page, int size ) --> 페이지 번호(0부터 시작), 한 페이지에 보여지는 글의 갯수
		//	5-2. PageRequest.of ( int page, int size , Sort sort ) --> 페이지, 사이즈, 정렬방향
		//	5-3. PageRequest.of ( int page, int size, Sort.Direction.Asc[Desc], String column ) -> 페이지, 사이즈, 정렬방향, 적용컬럼

		// 5-3
		// Pageable pageable = PageRequest.of(1, 2, Sort.Direction.DESC, "name");
		// 5-2 : page 부분은 pageable.getPageNumber() 를 사용할 것. 테스트라서 0으로 사용함.
		Pageable pageable = PageRequest.of(0, 3, Sort.by("name"));

		Page<Member> members = memberRepository.findAll(pageable);
		members.stream().forEach(member -> {
			System.out.println(member);
		});

	}
}
