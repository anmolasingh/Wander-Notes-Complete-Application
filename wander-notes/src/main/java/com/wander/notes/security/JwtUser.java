package com.wander.notes.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	//private final String id;
    private final String username;
    private final String name;
    //private final String lastname;
    private final String password;
    //private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
          //String id,
          String username,
          String name,
          //String lastname,
          //String email,
          String password, Collection<? extends GrantedAuthority> authorities,
          boolean enabled,
          Date lastPasswordResetDate
    ) {
        //this.id = id;
        this.username = username;
        this.name = name;
        //this.lastname = lastname;
        //this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getName() {
        return name;
    }

    /*public String getLastname() {
        return lastname;
    }*/

    /*public String getEmail() {
        return email;
    }*/

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

}
