package edu.ufape.lmts.listalk.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter;

    public WebSecurityConfig(JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter) {
        this.jwtAuthenticationTokenConverter = jwtAuthenticationTokenConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("/api-doc/**").permitAll()//depois definimos o padrão da requisição e passamos a role do client
                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationTokenConverter)))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(customizer -> customizer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))

                .build();
    }
}