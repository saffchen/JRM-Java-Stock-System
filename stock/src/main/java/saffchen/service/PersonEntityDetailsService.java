package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import saffchen.entities.PersonEntity;
import saffchen.repository.PersonsRepository;
import saffchen.security.PersonDetails;

import java.util.Optional;

@Service("personEntityDetailService")
public class PersonEntityDetailsService implements UserDetailsService {

    private final PersonsRepository personsRepository;

    @Autowired
    public PersonEntityDetailsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonEntity> personEntity = personsRepository.findByEmail(username);
        if (personEntity.isEmpty())
            throw new UsernameNotFoundException("User wasn't found!");
        return new PersonDetails(personEntity.get());
    }
}
