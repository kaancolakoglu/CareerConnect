package com.springframework.CareerConnect.security;

import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.domain.User;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenUtil.validateToken(token)) {
                UserDetails userDetails = getUserDetails(token);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails getUserDetails(String token) {
        Claims claims = jwtTokenUtil.parseClaims(token);
        String subject = claims.getSubject();
        String roles = claims.get("roles", String.class);

        String[] jwtSubjectArray = subject.split(",");

        User user = new User();
        user.setProfileId(user.getProfileId());
        //user.setEmail(jwtSubjectArray[1]);

        Set<Role> roleSet = Arrays.stream(roles.split(","))
                .map(roleName -> new Role(roleName.trim()))
                .collect(Collectors.toSet());
        user.setRoles(roleSet);

        return new CustomUserDetails(user);
    }
}
