package saffchen.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import saffchen.entities.PersonEntity;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class PersonDetails implements UserDetails {
    private final PersonEntity personEntity;

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

    public PersonEntity getPersonEntity(){
        return personEntity;
    }
}