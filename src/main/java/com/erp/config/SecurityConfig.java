//package com.erp.config;
//
//import com.erp.jwt.JwtAuthenticationEntryPoint;
//import com.erp.jwt.JwtAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtAuthenticationEntryPoint entryPoint;
//    private final UserDetailsService userDetailsService;
//    private final JwtAuthenticationFilter filter;
//
//    private static final String[] AUTH_WHITE_LIST={
//            // Swagger paths
//            "/swagger-resources/**",
//            "/swagger-ui.html",
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/webjars/**",
//            "/swagger-resources/**",
//            "/swagger-resources/configuration/security"
//    };
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
//            http.csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth-> auth
//                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
//                        .requestMatchers("/authController/login").permitAll()
//                        .requestMatchers("/authController/getUsersRole/{userName}").permitAll()
//                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
//                        .anyRequest().authenticated())
//                 .exceptionHandling(ex->ex.authenticationEntryPoint(entryPoint))
//                 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .userDetailsService(userDetailsService) // ✅ No more deprecated DaoAuthenticationProvider
//                 .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//
//         return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return  new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//}
