package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.AuthenticationToken;
import com.newoneplus.dresshub.Model.Member;
import com.newoneplus.dresshub.Repository.MemberRepository;
import com.sun.tools.javac.comp.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest")
@Slf4j
public class RestMemberController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MemberRepository memberRepository;




    // Read
    @RequestMapping(value = "{uid}", method = RequestMethod.GET)
    public Member findOne(@PathVariable(value = "uid") String uid) {
        return memberRepository.findByUid(uid);
    }


    // Read(모든사용자)
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Member> list() {
        return memberRepository.findAll();
    }

    // Login
    @PostMapping(value = "/login")
    @ResponseBody
    public AuthenticationToken login(@ModelAttribute AuthenticationRequest authenticationRequest, HttpSession session) {
        String uid = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uid, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        Member member = memberRepository.findByUid(uid);
        log.info("========login=========");
        return new AuthenticationToken(member.getUid(), member.getRoles(), session.getId());
    }

//     UPDATE
//     해당 ID의 사용자 이름을 갱신한 뒤 그 결과를 반환
    //TODO 수정예정
//     모든 데이터 수정가능하게?
    @RequestMapping(value = "update/{uid}", method = RequestMethod.POST)
    public Member update(@PathVariable(value = "uid") String uid, @RequestParam(value = "name") String name) {
        Member member = memberRepository.findByUid(uid);
        member.setName(name);
        return memberRepository.save(member);
    }

//     DELETE
//     해당 ID의 사용자를 삭제
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "uid") String uid) {
        memberRepository.deleteById(uid);
    }


}
