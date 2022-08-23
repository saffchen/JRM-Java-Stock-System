package saffchen.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import saffchen.security.JWTSecurityFilter;
import saffchen.service.UserEntityDetailsService;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final UserEntityDetailsService userEntityDetailsService;
    private final JWTSecurityFilter jwtSecurityFilter;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/api/v1/auth/check_auth").permitAll()
                .mvcMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/api/v1/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/api/v1/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/api/v1/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userEntityDetailsService).
                passwordEncoder(getPasswordEncoder());

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}