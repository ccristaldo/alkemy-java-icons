package com.fmalessio.alkemy.icons.utils;

import com.fmalessio.alkemy.icons.auth.entity.UserEntity;
import com.fmalessio.alkemy.icons.auth.service.JwtUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class AuthResolver {

    private static AuthResolver instance;
    private static String TOKEN_PREFIX = "Bearer ";
    private String token;
    private String jwt;

    private AuthResolver() {
        JwtUtils jwtUtils = new JwtUtils();
        this.jwt = jwtUtils.generateToken(getUserDatailsMock());
        this.token = TOKEN_PREFIX + jwt;
    }

    public static AuthResolver getInstance() {
        if (instance == null) {
            instance = new AuthResolver();
        }
        return instance;
    }

    public UserEntity getUserEntityMock() {
        UserEntity userMock = new UserEntity();
        userMock.setUsername("mock@mock.com");
        userMock.setPassword("Password123!");
        return userMock;
    }

    private UserDetails getUserDatailsMock() {
        UserDetails userDetailsMock = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "Password123!";
            }

            @Override
            public String getUsername() {
                return "mock@mock.com";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        return userDetailsMock;
    }

}
