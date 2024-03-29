package saffchen.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import saffchen.service.UserEntityDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTSecurityFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserEntityDetailsService userEntityDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String prefix = "Bearer";
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(prefix)) {
            String onlyToken = authHeader.substring(prefix.length() + 1).strip();
            if (onlyToken.isBlank()) {
                log.error("Invalid JWT token in Bearer header!");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token in Bearer header!");
            } else {
                try {
                    String username = jwtUtil.validateTokenAndGetClaim(onlyToken);
                    UserDetails userDetails = userEntityDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } catch (JWTVerificationException e) {
                    log.error("JWTVerificationException: {}", e.getMessage());
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid JWT Token!");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
