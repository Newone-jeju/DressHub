package com.newoneplus.dresshub.Config;


import com.newoneplus.dresshub.Service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsUtils;

import org.springframework.session.*;
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;





    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .formLogin()

//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//                .successHandler(successHandler())
//                .failureUrl("/login?error")
//                .and()
//                .logout();
                http.logout();
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true);

        http.sessionManagement().

//                sessionFixation().
//                migrateSession().
//                invalidSessionUrl("/").
//                maximumSessions(1).
//                maxSessionsPreventsLogin(true).
//                expiredUrl("/login?duplicate");

//                and().
                sessionCreationPolicy(SessionCreationPolicy.NEVER);



//        http.csrf().disable();
        http.csrf()
                .ignoringAntMatchers("/product/*")
                .ignoringAntMatchers("/review/*")
                .ignoringAntMatchers("/leaseInfo/*")
                .ignoringAntMatchers("/rest/*");

        //        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true);


    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        //이전 url 정보 기억 관련..
        return new CustomLoginSuccessHandler("/defaultUrl");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }


    @Bean
    public HttpSessionIdResolver httpSessionStrategy(){
        log.info("=========== HttpSession Resolver =============== ");
        return HeaderHttpSessionIdResolver.xAuthToken();
//        return new HeaderHttpSessionIdResolver("x-auth-token");
    }

//"Access-Control-Allow-Headers", "x-auth-token, content-type"
//    @Bean
//    public HttpSessionStrategy httpSessionStrategy()
//    {
//        return new HeaderHttpSessionStrategy();
//    }



}
