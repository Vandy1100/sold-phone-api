package com.shop.soldphonev2.security;


import com.shop.soldphonev2.api.auth.Auth;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserDetailsImpl implements UserDetails {
    private int id;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
    private Boolean verified;
    public UserDetailsImpl(Auth auth){
        this.authorities = auth.getRoles().stream().map((SimpleGrantedAuthority::new))
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return verified;
    }
}
