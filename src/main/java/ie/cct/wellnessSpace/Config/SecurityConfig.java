package ie.cct.wellnessSpace.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
        Spring Security configuration class, code based on spring security course by Romanian Coder (2019)
     */
    private UserDetailsService userDetailsServiceImp;
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Autowired
    SecurityHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    /*
        Declaring which pages needs authentication to be opened, which needs authorization by having rule = admin
        fixing my own login page instead spring security login page
        Setting sucessHandler class as the responsible to deal with the redirect after authentication
        Setting logout page
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/resources","/","/index", "/img/**","/webjars/**", "/CSS/**", "/signup")
                .permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/templates/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureUrl("/login?error")
                .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
    }

    /*
        Dao authentication provider servive to handle the data conference between what the application is passing and what is in the data base
        using BCrypt and userDetailsService to get all information.
     */
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsServiceImp);
        return daoAuthenticationProvider;
    }
    /*
        Bean for BCrypt passwordEncoder to be used to the authentication provider to check the password, once
        the passwords are saved encoded in the database.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }




}
