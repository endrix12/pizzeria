package pl.abusko.pizzeria.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.abusko.pizzeria.User.User;
import pl.abusko.pizzeria.User.UserDetailServiceImpl;
import pl.abusko.pizzeria.User.UserService;


@Configuration
@NoArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailServiceImpl userDetailsService;
    UserService userService;


    @Autowired
    public WebSecurityConfig(UserDetailServiceImpl userDetailsService, UserService userService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  auth.inMemoryAuthentication().withUser(new User("andrzejbusko610@gmail.com",passwordEncoder().encode("haneczka"), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/register").permitAll()
                .and().formLogin().permitAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();

    }

    @EventListener(ApplicationReadyEvent.class)
    public void addAdmin() {
        User admin = new User(1L, "andrzejbusko610@gmail.com", passwordEncoder().encode("haneczka"), "Andrzej", "Buśko", 603787609, "ROLE_ADMIN");
        userService.add(admin);
    }

}
