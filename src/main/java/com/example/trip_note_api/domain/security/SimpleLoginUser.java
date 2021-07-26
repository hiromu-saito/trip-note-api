package com.example.trip_note_api.domain.security;


import com.example.trip_note_api.domain.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {

    private User user;
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    public User getUser() {
        return user;
    }

    public SimpleLoginUser(User user) {
        super(user.getMailAddress(), user.getPassword(), ADMIN_ROLES);
        this.user = user;
    }
}
