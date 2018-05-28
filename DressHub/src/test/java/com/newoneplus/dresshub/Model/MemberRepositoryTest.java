package com.newoneplus.dresshub.Model;

import com.newoneplus.dresshub.Repository.MemberRepository;
import lombok.extern.java.Log;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/
        memberRepository.deleteAll();
    }
    @Test
    public void insertTest() {
        for(int i=0; i<10; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setPassword("pw" + i);
            member.setEmail("hihi@" + i);
            member.setName("ming" + i);
            member.setAddress("jeju" + i);
            member.setPhoneNumber("010123456"+i);
            member.setNickname("m" + i);
            MemberRole role = new MemberRole();
            if(i <= 4) {
                role.setRoleName("BASIC");
            }else if(i <= 7) {
                role.setRoleName("MANAGER");
            }else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }
    }

    @Test
    public void testMember() {
        Optional<Optional<Member>> result = Optional.ofNullable(memberRepository.findById(5L));
        result.ifPresent(member -> log.info("member " + member));
    }

}
