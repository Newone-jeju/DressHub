//package com.newoneplus.dresshub;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    UserDao userDao;
//
////    // 테스트용으로 임시 매핑
////    @RequestMapping(value = "", method = RequestMethod.GET)
////    public String index(){
////        return "index";
////    }
//
//    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
//    public String login(){
//        return "login";
//    }
//
//    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
//    public String loginCheck(HttpServletRequest httpServletRequest, @ModelAttribute User user){
//        boolean result;
//        String id = user.getId();
//        String password = user.getPassword();
//        if (user != null && id != null && password != null){
//            result = userDao.isValidUser(id, password);
//            if (result == true){
//                return "index";
//            } else {
//                return "login";
//            }
//        }
//        return "login";
//    }
//
//    @RequestMapping("/logout")
//    public String logout(HttpSession session){
////        userService.logout(session);
//        return "login";
//    }
//
//    @RequestMapping(value = "/join", method = RequestMethod.GET)
//    public String signUp(){
//        return "join";
//    }
//
//    // 회원가입
//    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
//    public String userSignUp(){
//        return null;
//    }
//
//
//
//
//
//
//
//
//}
