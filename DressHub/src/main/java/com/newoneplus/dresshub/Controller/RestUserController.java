package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.ApiResponseMessage;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Model.UserRole;
import com.newoneplus.dresshub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class RestUserController {
    private final UserRepository userRepository;
    @GetMapping("/{uid}")
    public User get(@PathVariable String uid) {
        return userRepository.findByUid(uid);
    }
    @GetMapping("/list")
    public List<User> list() {
        return userRepository.findAll();
    }

    @PostMapping
    public ApiResponseMessage create(@RequestBody User user) {
        if (user.getUid() == "" || user.getName() == "") {
            return new ApiResponseMessage(HttpStatus.BAD_REQUEST, 400);
        }
        try {
            UserRole role = new UserRole();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            role.setRoleName("ROLE_USER");
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
            return new ApiResponseMessage(HttpStatus.OK, 200);
        } catch (Exception ex){
            return new ApiResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, 500, ex);
        }
    }

    @PutMapping("/{uid}")
    public void modify(@RequestBody User user, @PathVariable String uid) {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userRepository.delete(userRepository.findById(id).get());
    }


}
