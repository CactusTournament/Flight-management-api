
package com.sprint.flightapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

        // Add authentication failure handler for debug logging
        @Bean
        public org.springframework.security.web.authentication.AuthenticationFailureHandler authenticationFailureHandler() {
            return (request, response, exception) -> {
                System.out.println("[DEBUG] Authentication failed: " + exception.getMessage());
                response.sendError(org.springframework.http.HttpStatus.UNAUTHORIZED.value(), "Authentication Failed: " + exception.getMessage());
            };
        }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults()) // Enable CORS with default config
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/passengers/signup").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access /admin/**
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic
                .authenticationEntryPoint((request, response, authException) -> {
                    System.out.println("[DEBUG] HTTP Basic authentication failed: " + authException.getMessage());
                    response.sendError(org.springframework.http.HttpStatus.UNAUTHORIZED.value(), "HTTP Basic Authentication Failed: " + authException.getMessage());
                })
            );
        return http.build();
    }

    // CORS configuration bean for Spring Security
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOrigins(java.util.List.of("http://localhost:5173"));
        configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(java.util.List.of("*"));
        configuration.setAllowCredentials(true);
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
