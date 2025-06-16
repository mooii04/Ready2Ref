package com.salesianos.triana.DoradoMoises_Ready2Ref.security;

import com.salesianos.triana.DoradoMoises_Ready2Ref.security.exceptionhandling.JwtAccessDeniedHandler;
import com.salesianos.triana.DoradoMoises_Ready2Ref.security.exceptionhandling.JwtAuthenticationEntryPoint;
import com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.access.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.authenticationProvider(authenticationProvider())
                        .build();

        return authenticationManager;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();

        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder);
        return p;

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(excepz -> excepz
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
       http.authorizeHttpRequests(authz -> authz
    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
    .requestMatchers(HttpMethod.POST,
        "/auth/login",
        "/activate/account",
        "/auth/refresh/token",
        "/error",
        "/download/**",
        "/edit/**",
        "/mensaje/search",
        "/swagger-ui/**",
        "/download/**"
    ).permitAll()

    // Rutas protegidas por rol
    .requestMatchers("/me/admin", "/arbitro/create/**", "/entrenador/create",
        "/arbitro/edit/admin/**", "/arbitro/search/", "/delete/**", "/mensaje/create/**"
    ).hasRole("ADMIN")

    .requestMatchers("/entrenador/**", "/me/entrenador", "/upload").hasRole("ENTRENADOR")

    .requestMatchers("/me/user", "/arbitro/edit/user/me").hasRole("USER")

    // Rutas abiertas (H2 y Swagger)
    .requestMatchers("/h2-console/**", "/swagger-ui/**").permitAll()

    // Importante: proteger cualquier otro endpoint bajo /me/**
    .requestMatchers("/yo").authenticated()

    // Todo lo demás requiere autenticación
    .anyRequest().authenticated()
);


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOriginPatterns(
                                List.of("http://localhost:4200", "http://host.docker.internal:4200"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(
                                List.of("Authorization", "Content-Type", "content-type", "Accept", "X-Requested-With"));
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L); // Cache de opciones CORS por 1 hora

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

}
