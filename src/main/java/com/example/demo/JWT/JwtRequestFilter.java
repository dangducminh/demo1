package com.example.demo.JWT;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@Primary
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService UserService;

    @Autowired
    private JwtCreateToken jwtCreatToken;

    @Autowired
    UserDetails userDetails;

    @Autowired
    UserDetailsService userDS;

    @Autowired
    public static HttpServletRequest requestPublic;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        try {
            String jwt = parseJWT(request);
            requestPublic = request;
            if (jwt != null && jwtCreatToken.validateToken(jwt)) {
                String gmail = jwtCreatToken.getUserNameFromJwtToken(jwt);

                userDetails = userDS.loadUserByUsername(gmail);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch(Exception e){
            logger.error("Cannot set user authencation: {}",e);
        }
        chain.doFilter(request, response);
    }

    private String parseJWT(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(requestTokenHeader)&& requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }
        return null;
    }
}
