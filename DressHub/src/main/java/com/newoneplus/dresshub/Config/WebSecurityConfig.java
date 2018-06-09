
package com.newoneplus.dresshub.Config;

import com.newoneplus.dresshub.Service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

//import java.security.AuthProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 보안

    @Autowired
    AuthProvider authProvider;



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**","/image/**", "/static/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**").permitAll();
//                .antMatchers("/", "/login", "/login?error", "/login?logout", "/products/**", "/login?duplicate", "/join/**").permitAll()
//                .antMatchers("/**").authenticated();
//        https test code
//        http.requiresChannel()
//        .antMatchers("/**").requiresSecure();
//        csrf config
        http.csrf()
                .ignoringAntMatchers("/products/*")
                .ignoringAntMatchers("/review/*")
                .ignoringAntMatchers("/leaseInfo/*");

        http.sessionManagement().
                sessionFixation().
                migrateSession().
//                invalidSessionUrl("/").
                maximumSessions(1).
                maxSessionsPreventsLogin(false).

                expiredUrl("/login?duplicate");


        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/loginSuccess")
                .usernameParameter("uid")
                .passwordParameter("password");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID", "uid")
                .invalidateHttpSession(true);

        http.authenticationProvider(authProvider);



    }





}

