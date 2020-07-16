package ie.cct.wellnessSpace.Config;

import ie.cct.wellnessSpace.Repository.UserRepository;
import ie.cct.wellnessSpace.Services.UserDetailsServiceImp;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsServiceImp;
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
        web.ignoring().antMatchers("/index");
        web.ignoring().antMatchers("/bodyTreatments");
        web.ignoring().antMatchers("/facialTreatments");
        web.ignoring().antMatchers("/mentalTreatments");
        web.ignoring().antMatchers("/signup");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/resources","/","/index", "/img/**","/webjars/**", "/CSS/**", "/signup")
                .permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .antMatchers("/templates/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/signin").permitAll()
                .loginProcessingUrl("/myAccount")
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .failureUrl("/signin?error")
                .and()
                .logout()
                    .logoutSuccessUrl("/signin?logout")
                    .permitAll();
    }

    @Bean
    UserDetailsServiceImp userDetailsServiceImp(){
        return new UserDetailsServiceImp(userRepository);
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImp());
        return daoAuthenticationProvider;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
