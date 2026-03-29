package com.security.security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    //step1= inject JWT Service
    @Autowired
    private JWTService jwtService;

    //Step2= use doFilterInternal method it will run every time when client sends request
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       String path = request.getRequestURI();
        // skip login endpoint
        if (path.equals("/linkedIn/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        //step3= get Authentication header, why JWT always send in header without reading header can't create JWT
        String header = request.getHeader("Authorization");

        //Step 4= check header exists & starts with bearer if header missing continue
        if (header == null || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        //extract token
        String token = header.substring(7); // remove bearer name from token
        //extract username from token
        String username = jwtService.extractUsername(token);
        String role = jwtService.extractRole(token);  // which user making request
        //create authentication object, this obj represent authenticated user
        //contains principle-username, credentials-password, authorities-roles
        //check is user authenticated or not, what permissions user has
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
                (username, null,  List.of(new SimpleGrantedAuthority("ROLE_" + role)));
        System.out.println("User = " + username + " | Role = " + role);

        //it is spring security memory u r telling user is authenticated and after this spring allows access to controller
        SecurityContextHolder.getContext().setAuthentication(auth);


    //pass request to next filter or controller without this req stops
        filterChain.doFilter(request,response);


}
}
