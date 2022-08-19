package saffchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.PersonAuthResponse;
import saffchen.dto.PersonAuthRequest;
import saffchen.security.JwtUtil;
import saffchen.security.PersonDetails;
import saffchen.service.PersonEntityDetailsService;

import java.util.stream.Collectors;

@RestController
public class AuthController {
    private final JwtUtil jwtUtil;
    private final PersonEntityDetailsService personEntityDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JwtUtil jwtUtil, PersonEntityDetailsService personEntityDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.personEntityDetailsService = personEntityDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/check_auth", method = RequestMethod.POST)
    public ResponseEntity<?> checkAuthAndGetToken(@RequestBody PersonAuthRequest personAuthRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            personAuthRequest.getUsername(),
                            personAuthRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect user or password!");
        }

        PersonDetails personDetails = (PersonDetails)personEntityDetailsService.
                loadUserByUsername(personAuthRequest.getUsername());

        String authority = personDetails.getAuthorities()
                .stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(""));
        String email = personDetails.getEmail();
        String jwt = jwtUtil.createToken(
                email,
                authority);

        return  ResponseEntity.ok(new PersonAuthResponse(jwt));
    }

}
