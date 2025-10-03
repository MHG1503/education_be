package com.mhgjoker.education.security.model;

import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtUser implements UserDetails {

    private Long id;

    private String fullname;

    private String username;

    private String email;

    private String password;

    private Boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(UserEntity user){
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.authorities = populateRoles(user.getRole());
    }

    public String getLoginUsername(){
        return username;
    }

    public List<GrantedAuthority> populateRoles(RoleEntity role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return enabled;
    }
}
