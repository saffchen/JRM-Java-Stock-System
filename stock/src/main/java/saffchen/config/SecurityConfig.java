package saffchen.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import saffchen.service.PersonEntityDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final PersonEntityDetailsService personEntityDetailsService;

    @Autowired
    public SecurityConfig(PersonEntityDetailsService personEntityDetailsService) {
        this.personEntityDetailsService = personEntityDetailsService;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/*").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                //.formLogin().disable()
                //Uncomment if the login page was created
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/check_auth")
                .failureUrl("/login")
                .and()
                .logout().logoutUrl("/logout")
                .deleteCookies()
                .logoutSuccessUrl("/login")
        ;
    }    //to set the authentification
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(personEntityDetailsService).
                passwordEncoder(getPasswordEncoder());

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}