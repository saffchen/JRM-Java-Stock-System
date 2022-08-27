package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import saffchen.entities.UserEntity;
import saffchen.repository.UserRepository;
import saffchen.security.UserDetails;

import java.util.Optional;

@Service
public class UserEntityDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> personEntity = userRepository.findByEmail(username);
        if (personEntity.isEmpty())
            throw new UsernameNotFoundException("User wasn't found!");
        return new UserDetails(personEntity.get());
    }
}
