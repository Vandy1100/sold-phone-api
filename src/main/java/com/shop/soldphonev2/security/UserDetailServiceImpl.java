package com.shop.soldphonev2.security;

import com.shop.soldphonev2.api.auth.UserDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    //Catch id it for add data
    final UserDetailMapper userDetailMapper;
    public UserDetailServiceImpl(UserDetailMapper userDetailMapper) {
        this.userDetailMapper = userDetailMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            log.info("Email received, {} " , email);
            var authenticatedUser = userDetailMapper.loadUserByUsername(email);

            if (authenticatedUser != null) {
                log.info("User from the database is, {}",authenticatedUser);
                UserDetailsImpl user = new UserDetailsImpl(authenticatedUser);

                user.setEmail(authenticatedUser.getEmail());
                user.setPassword(authenticatedUser.getPassword());

                user.setId(authenticatedUser.getId());
                user.setVerified(authenticatedUser.getVerified());

                // Convert roles to GrantedAuthority objects
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                for (String role : authenticatedUser.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                user.setAuthorities(List.of(authorities.toArray(new SimpleGrantedAuthority[0])));
                return user;
            } else {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
        } catch (Exception e) {
            log.info("Error while loading user by username, {}" , email);
            e.printStackTrace();
            throw new UsernameNotFoundException("Error while loading user by username: " + email);
        }
    }
}
