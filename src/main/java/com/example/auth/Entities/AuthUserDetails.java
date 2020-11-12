package com.example.auth.Entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDetails extends UsersEntity implements UserDetails {

    UsersEntity user;
    public AuthUserDetails(final UsersEntity users) {
        super(users);
        this.user = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for(AuthoritiesEntity authority : super.getAuthoritiesByUserName()){
            grantedAuthorityList.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return authority.getRole();
                }
            });
        }
        return grantedAuthorityList;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
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
        return true;
    }
}
