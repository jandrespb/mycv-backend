package com.jandcode.mycv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ DESHABILITA CSRF (porque es portafolio, sin sesiones)
                .csrf(csrf -> csrf.disable())

                // ✅ ACTIVA CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .authorizeHttpRequests(auth -> auth
                        // Permite OPTIONS (preflight de CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Permite GET /health sin autenticación
                        .requestMatchers(HttpMethod.GET, "/api/customers/health").permitAll()

                        // Permite POST /save sin autenticación
                        .requestMatchers(HttpMethod.POST, "/api/customers/save").permitAll()

                        // Rechaza todo lo demás
                        .anyRequest().denyAll()
                )

                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // ✅ Orígenes permitidos
        config.setAllowedOrigins(List.of(
                "http://localhost:5500",
                "http://127.0.0.1:5500",
                "https://jandtocode.com",
                "https://www.jandtocode.com"
        ));

        // ✅ Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "OPTIONS"));

        // ✅ Headers específicos (no "*")
        config.setAllowedHeaders(List.of(
                "Content-Type",
                "Accept",
                "Authorization"
        ));

        // ✅ Headers que el navegador puede leer
        config.setExposedHeaders(List.of("Content-Type"));

        // ✅ No permite credenciales (cookies)
        config.setAllowCredentials(false);

        // ✅ Cache del preflight por 1 hora
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}