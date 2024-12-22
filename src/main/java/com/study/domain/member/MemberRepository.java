package com.study.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	
	// truncate 실행  실데이터 있는 날라가니 테스트용으로만 사용
	@Transactional
	@Modifying
	@Query(value = "truncate table tb_member", nativeQuery = true)
	void truncate();
}
