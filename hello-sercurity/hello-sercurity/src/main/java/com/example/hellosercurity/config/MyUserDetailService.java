package com.example.hellosercurity.config;

import com.example.hellosercurity.entity.Account;
import com.example.hellosercurity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountService.findByUsername(username);
        if (!optionalAccount.isPresent()){
            return null;
        }
        Account account = optionalAccount.get();
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (account.getRole() == 1) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        }else if (account.getRole() == 2){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        UserDetails userDetails = new User(account.getUsername(),account.getPasswordHash(),simpleGrantedAuthorities);
        return userDetails;
    }
}
