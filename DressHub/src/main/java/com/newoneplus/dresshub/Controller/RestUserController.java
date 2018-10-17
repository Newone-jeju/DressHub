package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.ApiResponseMessage;
import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Model.UserRole;
//import com.newoneplus.dresshub.Model.UserRole;
import com.newoneplus.dresshub.Repository.UserRepository;
import com.newoneplus.dresshub.util.ApiFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class RestUserController {
    private final UserRepository userRepository;
    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/search/list")
    public List<User> list() {
        return userRepository.findAll();
    }



    @PostMapping("/signup")
    public ApiResponseMessage create(@RequestBody User user) {
        if (user.getUid() == "" || user.getName() == "") {
            return ApiFactory.badRequest();
        }
        try {
//            UserRole role = new UserRole();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            role.setRoleName("USER");
//            user.setRoles(Arrays.asList(role));
            user.setUserType(1);
            userRepository.save(user);
            return ApiFactory.accept();
        } catch (Exception ex){
            return ApiFactory.serverError();
        }
    }


    @PutMapping
    public ApiResponseMessage modify(@RequestBody User user) {
        if (userRepository.existsById(user.getId()) == true) {
            userRepository.save(user);
            return ApiFactory.accept();
        } else {
            return ApiFactory.badRequest();
        }
    }



//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id) {
//        userRepository.delete(userRepository.findById(id).get());
//    }


}
