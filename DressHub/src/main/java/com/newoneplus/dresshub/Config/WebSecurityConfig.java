package com.newoneplus.dresshub.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


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
//        http.csrf().disable();
        http.sessionManagement().
                sessionFixation().
                migrateSession().
                invalidSessionUrl("/").
                maximumSessions(1).
                maxSessionsPreventsLogin(false).

                expiredUrl("/login?duplicate");


        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/test")
                .usernameParameter("id")
                .passwordParameter("password");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        http.authenticationProvider(authProvider);




    }




}
