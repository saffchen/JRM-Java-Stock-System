package saffchen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.UserAuthRequest;
import saffchen.dto.UserAuthResponse;
import saffchen.security.JwtUtil;
import saffchen.security.UserDetails;
import saffchen.service.UserEntityDetailsService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AuthController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    static final String REST_URL = "/api/v1/auth/check_auth";
    private final JwtUtil jwtUtil;
    private final UserEntityDetailsService userEntityDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAuthAndGetToken(@RequestBody UserAuthRequest userAuthRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthRequest.getEmail(),
                            userAuthRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect user or password!");
        }

        UserDetails userDetails = (UserDetails) userEntityDetailsService.
                loadUserByUsername(userAuthRequest.getEmail());

        String authority = userDetails.getAuthorities()
                .stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(""));
        String email = userDetails.getEmail();
        String jwt = jwtUtil.createToken(
                email,
                authority);

        return ResponseEntity.ok(new UserAuthResponse(jwt));
    }
}
