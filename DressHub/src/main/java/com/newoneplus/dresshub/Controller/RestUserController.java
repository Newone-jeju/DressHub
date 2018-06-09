package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Config.AuthProvider;
import com.newoneplus.dresshub.Config.MyAuthentication;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Repository.UserRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class RestUserController {
    private final UserRepository userRepository;
    @GetMapping("/{id}")
//    @Secured("ROLE_USER")
    public User get(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }


}
