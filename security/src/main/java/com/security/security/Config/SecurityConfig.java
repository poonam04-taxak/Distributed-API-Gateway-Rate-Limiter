package com.security.security.Config;

import com.security.security.JWT.JWTAuthenticationFilter;
import com.security.security.JWT.RateLimiter.RateLimitingFilter;
import com.security.security.Redis.RateLimiter.RedisRateLimitingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

  //  private RateLimitingFilter rateLimitingFilter;
    private JWTAuthenticationFilter jwtAuthenticationFilter;
 //   private RedisRateLimitingFilter redisRateLimitingFilter;

    public SecurityConfig(//RateLimitingFilter rateLimitingFilter,
                          JWTAuthenticationFilter jwtAuthenticationFilter,
                          RedisRateLimitingFilter redisRateLimitingFilter){
     //   this.rateLimitingFilter=rateLimitingFilter;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    //    this.redisRateLimitingFilter=redisRateLimitingFilter;
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       httpSecurity
               .csrf(csrf->csrf.disable())
               .authorizeHttpRequests(auth->auth
                       .requestMatchers("/linkedIn/login").permitAll()
                       .requestMatchers("/linkedIn/del/**").hasRole("ADMIN")
                       .requestMatchers("/linkedIn/addJob").hasAnyRole("ADMIN", "USER")
                       .anyRequest()
                       .authenticated())
               //rate limiter runs first
              // .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class)


               // use redis limit filter
               //.addFilterBefore(redisRateLimitingFilter, UsernamePasswordAuthenticationFilter.class)
               //jwt runs after rate limiter
               .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//               .httpBasic(Customizer.withDefaults());
       return httpSecurity.build();
   }
}