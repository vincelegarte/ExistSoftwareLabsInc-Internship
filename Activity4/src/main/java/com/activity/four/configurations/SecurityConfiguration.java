package com.activity.four.configurations;

import com.activity.four.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.activity.four.security.authorities.ApplicationRole.ADMIN;
import static com.activity.four.security.authorities.ApplicationRole.USER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserPrincipalDetailsService userPrincipalDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/v1/employees").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers(HttpMethod.GET, "/api/v1/tickets").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers(HttpMethod.POST).hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE).hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT).hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(this.userPrincipalDetailsService);
        return provider;
    }

}
