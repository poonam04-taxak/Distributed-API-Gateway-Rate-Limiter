package com.security.security.JWT.RateLimiter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    //step 1= create concurrentHashMap to store requestCount safely, it will store IP address and request count
    private final ConcurrentHashMap<String, Integer> reqCount = new ConcurrentHashMap<>();

    // step2= use limit
    private final int Max_Count=5;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // step 3= gets user IP address
        String IP = request.getRemoteAddr(); // also can use username String username = jwtService.extractUsername(token);

        //step 4= if IP not in map add it with count 0
        reqCount.putIfAbsent(IP,0);

        //step 5= current count
        int count = reqCount.get(IP);

        //step 6= count limit
        if(count>=Max_Count){
            response.setStatus(429);
            response.getWriter().write("Sorry you have Too many Requests. Please try after some time.");
            return;
        }
        //increase count request
        reqCount.put(IP, count+1);

        filterChain.doFilter(request,response);

    }
}
