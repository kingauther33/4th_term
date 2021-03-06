package com.example.hellosercurity.config;

import com.auth0.jwt.JWT;
import com.example.hellosercurity.dto.AccountDTO;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public ApiAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            //xu li login
            AccountDTO accountDTO = gson.fromJson(jsonData, AccountDTO.class);
            UsernamePasswordAuthenticationToken userToken
                    = new UsernamePasswordAuthenticationToken(accountDTO.getUsername(),accountDTO.getPassword());
           return authenticationManager.authenticate(userToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //login thanh cong
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.DATE, 7);
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(currentTime.getTime())
                .withIssuer("t2004e")
                .withClaim("role",user.getAuthorities().iterator().next().getAuthority())
                .sign(SecurityBean.algorithm());
        HashMap<String,String> map = new HashMap<>();
        map.put("access_token",accessToken);
        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(map));
    }
}
