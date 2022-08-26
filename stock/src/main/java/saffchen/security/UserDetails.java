package saffchen.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import saffchen.entities.UserEntity;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final UserEntity personEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(personEntity.getRole()));
    }

    @Override
    public String getPassword() {
        return personEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return personEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return personEntity.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return personEntity.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return personEntity.getActive();
    }

    @Override
    public boolean isEnabled() {
        return personEntity.getActive();
    }

    public String getEmail() {
        return personEntity.getEmail();
    }

}