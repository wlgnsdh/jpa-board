package com.study;

import com.study.domain.member.Gender;
import com.study.domain.member.Member;
import com.study.domain.member.MemberRepository;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Slf4j
@SpringBootTest
// @Order 어노테이션 사용위한 선언
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // 초기화
    @Order(1)
    @Test
    void test1() {
    	log.info("truncate");
        memberRepository.truncate();
    }
    
    // 회원 정보 생성
    @Order(2)
    @Test
    void test2() {
    	log.info("saveMember");
        Member saveParams = Member.builder()
                .loginId("dyl6266")
                .password("1234")
                .name("도뎡")
                .gender(Gender.M)
                .birthday(LocalDate.of(1994, 9, 12))
                .deleteYn(false)
                .build();

        Member member = memberRepository.save(saveParams);
        Assertions.assertEquals(member.getLoginId(), "dyl6266");
    }

    // 전체 회원 조회
    @Order(3)
    @Test
    void test3() {
    	log.info("findAllMember");
        memberRepository.findAll();
    }

    // 회원 상세정보 조회
    @Order(4)
    @Test
    void test4() {
    	log.info("findMemberById");
        Member member = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(member.getLoginId(), "dyl6266");
    }

    // 회원 정보 삭제
    @Order(5)
    @Test
    void tast5() {
    	log.info("deleteMemberById");
        memberRepository.deleteById(1L);
    }

}
