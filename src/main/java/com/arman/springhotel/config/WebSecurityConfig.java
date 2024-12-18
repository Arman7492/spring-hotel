package com.arman.springhotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Отключаем CSRF для упрощения
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/css/**", "/js/**").permitAll() // Публичные пути
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Доступ только для админа
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll() // Кастомная страница входа
                        .defaultSuccessUrl("/dashboard", true) // Перенаправление после успешного входа
                )
                .logout(logout -> logout.permitAll()); // Позволяем выход всем
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Создаем двух пользователей: admin и client
        UserDetails client = User.builder()
                .username("client")
                .password("{noop}123") // Пароль без шифрования
                .roles("CLIENT")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123") // Пароль без шифрования
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(client, admin);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
