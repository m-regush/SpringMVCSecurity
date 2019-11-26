package springUser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("springUser")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private AuthSuccessHandler authenticationSuccessHandler;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, AuthSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user").hasAnyAuthority("ADMIN", "USER")
                .and().csrf().disable()
                .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                .usernameParameter("name")
                .successHandler(authenticationSuccessHandler)
                .and().logout().logoutSuccessUrl("/login?logout").permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
