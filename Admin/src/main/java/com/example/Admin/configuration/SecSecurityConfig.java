package com.example.Admin.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig  {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/","/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login.html")
//                    .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/api/admins", true);
//
//        return http.build();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//    }
}