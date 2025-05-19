    package com.JBooks.J_books.config;

    import com.JBooks.J_books.config.AuthFilter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
    import org.springframework.web.filter.CorsFilter;

    import java.util.List;

    @Configuration
    @EnableMethodSecurity
    public class SecurityConfig {

        @Autowired
        private AuthFilter authFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .cors(cors -> cors.configurationSource(corsConfigurationSource())) // <--- adicionado aqui
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/login", "/login/registrar", "/livros/12").permitAll()
                            .anyRequest().authenticated()
                    )
                    .sessionManagement(sess -> sess
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }
        @Bean
        public UrlBasedCorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(List.of("http://localhost:3000"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);
            return source;
        }
    }
