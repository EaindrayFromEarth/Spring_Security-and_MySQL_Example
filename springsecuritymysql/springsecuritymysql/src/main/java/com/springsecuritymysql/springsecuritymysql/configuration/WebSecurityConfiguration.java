package com.springsecuritymysql.springsecuritymysql.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.springsecuritymysql.springsecuritymysql.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public DelegatingApplicationListener myDelegatingApplicationListener() {
        return new DelegatingApplicationListener();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        String loginPage = "/login";
        String logoutPage = "/logout";

        http
            .authorizeRequests()
            .requestMatchers("/").permitAll()
            .requestMatchers(loginPage).permitAll()
            .requestMatchers("/registration").permitAll()
            .requestMatchers("/admin/**").hasAuthority("ADMIN")
            .anyRequest()
            .authenticated()
            .and().csrf().disable()
            .formLogin()
            .loginPage(loginPage)
            .loginPage("/")
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/admin/home")
            .usernameParameter("user_name")
            .passwordParameter("password")
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
            .logoutSuccessUrl(loginPage).and().exceptionHandling();
    }

    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}



