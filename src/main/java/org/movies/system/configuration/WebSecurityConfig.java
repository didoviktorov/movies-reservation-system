package org.movies.system.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**").permitAll()
                .antMatchers("/register").access("isAnonymous()")
                .antMatchers("/movies/show").access("isAuthenticated()")
                .antMatchers("/movies/add").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/logout").access("isAuthenticated()")
                .and().formLogin().loginPage("/login").permitAll()
                .passwordParameter("password")
                .usernameParameter("username").failureUrl("/login?error=true")
                .and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/unauthorized")
                .and().csrf().disable().userDetailsService(this.userDetailsService);
    }
}
