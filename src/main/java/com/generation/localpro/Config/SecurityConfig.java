package com.generation.localpro.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.mapper.PortalUserMapper;
import com.generation.localpro.model.PortalUser;

import java.util.List;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    String contentType = "application/json";

    private final PortalUserService userService;
    private final PortalUserMapper userMapper;
    private final ObjectMapper objectMapper;

   @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/public/**", "/api/auth/login").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated())

            .formLogin(form -> form
                    .loginProcessingUrl("/api/auth/login")
                    .usernameParameter("username")
                    .passwordParameter("password")

                    .successHandler((req, res, auth) -> {
                        res.setContentType(contentType);
                        res.setStatus(HttpServletResponse.SC_OK);

                        // fetch the full user and map to DTO
                        PortalUser user = userService.findByUserName(auth.getName());
                        PortalUserResponseDTO userDto = userMapper.toResponseDto(user);

                        // wrap in a response with message + full user DTO
                        Map<String, Object> body = Map.of(
                            "message", "Login successful",
                            "user", userDto
                        );

                        objectMapper.writeValue(res.getWriter(), body);
                    })

                    .failureHandler((req, res, ex) -> {
                        res.setContentType(contentType);
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        res.getWriter().write("{\"message\":\"Invalid credentials\"}");
                    })
                    .permitAll()
            )
            .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setContentType(contentType);
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("{\"error\":\"Unauthorized - Please login first\"}");
                    })
            )
            .logout(logout -> logout
                    .logoutUrl("/api/auth/logout")
                    .logoutSuccessHandler((req, res, auth) -> {
                        res.setStatus(HttpServletResponse.SC_OK);
                        res.getWriter().write("{\"message\":\"Logged out\"}");
                    })
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            .csrf(AbstractHttpConfigurer::disable)
            .authenticationProvider(authenticationProvider)
            .build();
    }



     @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // This MUST match your Angular URL exactly. Do not use "*" when allowCredentials is true
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); 
        
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        
        // This is crucial because your Angular Auth service uses withCredentials: true
        configuration.setAllowCredentials(true); 
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply this CORS configuration to all endpoints
        source.registerCorsConfiguration("/**", configuration); 
        return source;
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }


  @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
}
}