package saffchen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.PersonAuthResponse;
import saffchen.dto.PersonAuthRequest;
import saffchen.security.JwtUtil;
import saffchen.security.UserDetails;
import saffchen.service.UserEntityDetailsService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserEntityDetailsService userEntityDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/v1/auth/check_auth")
    public ResponseEntity<?> checkAuthAndGetToken(@RequestBody PersonAuthRequest personAuthRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            personAuthRequest.getEmail(),
                            personAuthRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect user or password!");
        }

        UserDetails userDetails = (UserDetails) userEntityDetailsService.
                loadUserByUsername(personAuthRequest.getEmail());

        String authority = userDetails.getAuthorities()
                .stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(""));
        String email = userDetails.getEmail();
        String jwt = jwtUtil.createToken(
                email,
                authority);

        return ResponseEntity.ok(new PersonAuthResponse(jwt));
    }

}
