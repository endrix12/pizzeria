package pl.abusko.pizzeria.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pl.abusko.pizzeria.ingredient.Ingredient;
import pl.abusko.pizzeria.Product.Pizza;
import pl.abusko.pizzeria.Product.PizzaRepository;
import pl.abusko.pizzeria.Role.Role;
import pl.abusko.pizzeria.Role.RoleRepository;
import pl.abusko.pizzeria.User.User;
import pl.abusko.pizzeria.User.UserDetailServiceImpl;
import pl.abusko.pizzeria.User.UserService;


@Configuration
@NoArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailServiceImpl userDetailsService;
    UserService userService;
    RoleRepository roleRepository;
    PizzaRepository pizzaRepository;

    @Autowired
    public WebSecurityConfig(UserDetailServiceImpl userDetailsService, UserService userService, RoleRepository roleRepository, PizzaRepository pizzaRepository) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        this.pizzaRepository = pizzaRepository;

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
                .antMatchers("/delete/**").hasRole("ADMIN")
                .and().formLogin().permitAll();

        http.headers().frameOptions().disable();
        http.csrf().disable();


    }


    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void addAdmin() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User admin = new User("andrzejbusko610@gmail.com", passwordEncoder().encode("haneczka"), "Andrzej", "Buśko", 603787609);
        User user = new User("busko610@gmail.com", passwordEncoder().encode("haneczka"), "Andrzej", "Buśko", 603787609);

        admin.addRole(roleRepository.getById(1));
        admin.addRole(roleRepository.getById(2));
        userService.add(admin);
        user.addRole(roleRepository.getById(2));
        userService.add(user);


        Ingredient tomatoSous = new Ingredient("sos pomidorowy");
        Ingredient cheese = new Ingredient("ser");

        Pizza margarita = new Pizza("margarita");
        margarita.addIngredient(tomatoSous);
        margarita.addIngredient(cheese);


        pizzaRepository.save(margarita);

    }

}
