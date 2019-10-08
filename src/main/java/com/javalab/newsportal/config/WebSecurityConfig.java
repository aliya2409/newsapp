package com.javalab.newsportal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select users.username, users.password, users.enabled "
                        + "from users "
                        + "where users.username = ?")
                .authoritiesByUsernameQuery("select authorities.username, authorities.authority as role "
                        + "from authorities "
                        + "where authorities.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/news/delete/**").hasRole("ADMIN")
                .antMatchers("/comments/delete/**").hasRole("ADMIN")
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/news/allnews", true)
                .failureUrl("/login?error=true").and()
                .logout()
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
