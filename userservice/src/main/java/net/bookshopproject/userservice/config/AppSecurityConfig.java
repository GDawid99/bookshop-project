package net.bookshopproject.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("qwerty1"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("qwerty2"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("qwertyuiop"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry ->
                        registry.requestMatchers("/api/**").permitAll()
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
