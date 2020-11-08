package com.psb.psbloanassignment.config;

import com.psb.psbloanassignment.service.UserSecurityService;
import com.psb.psbloanassignment.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/register/showRegistrationForm"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll().anyRequest().authenticated();

        http.
                csrf().disable().cors().disable()
                .formLogin()
                    .loginPage("/login").defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout").deleteCookies("remember-me").permitAll()
                .and()
                    .rememberMe();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}
