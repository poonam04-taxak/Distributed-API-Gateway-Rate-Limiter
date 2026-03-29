package com.security.security.Redis.RateLimiter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;

@Component
public class RedisRateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final int MAX_REQUESTS=5;
    private final int WINDOW_SECONDS=60;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if(path.equals("/linkedIn/login")){
            filterChain.doFilter(request,response);
            return;
        }

        String IP = request.getRemoteAddr();
        String key = "rate_limit: " +IP;
        String CountStr=redisTemplate.opsForValue().get(key);
        int count = CountStr==null?0:Integer.parseInt(CountStr);
        if(count>=MAX_REQUESTS){
            response.setStatus(439);
            response.getWriter().write("Too many request. Try again later");
            return;
        }
        redisTemplate.opsForValue().increment(key);
        redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SECONDS));

        filterChain.doFilter(request,response);
    }
}
