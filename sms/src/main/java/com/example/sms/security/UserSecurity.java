package com.example.sms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class UserSecurity {

    @Autowired
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http)throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/user/signUp","/user/login").permitAll()
//                                .requestMatchers("/**").hasAnyRole("USER","SUPER_USER","ADMIN","SUPER_ADMIN")
//                                .requestMatchers("/**").hasAnyRole("SUPER_USER","ADMIN","SUPER_ADMIN")
//                                .requestMatchers("/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                                .requestMatchers("/**").hasRole("SUPER_ADMIN")
                                .anyRequest().authenticated());
//                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
