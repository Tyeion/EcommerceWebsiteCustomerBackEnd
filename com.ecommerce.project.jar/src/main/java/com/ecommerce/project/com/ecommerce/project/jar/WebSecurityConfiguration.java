//package com.ecommerce.project.com.ecommerce.project.jar;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
//public class WebSecurityConfiguration {
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/capstone/all").permitAll()
//                        .requestMatchers("/capstone/search/{id}").permitAll()
//                        .requestMatchers("/capstone/delete/{id}").hasRole("ADMIN")
//                        .requestMatchers("capstone/add").hasRole("ADMIN")
//                        .requestMatchers("capstone/search").permitAll()
//                        .requestMatchers("capstone/{id}").hasRole("ADMIN")
//                        .requestMatchers("/Cart/add").permitAll()
//                        .requestMatchers("Cart/items").permitAll()
//                        .requestMatchers("Cart/remove/{cartItemId}").permitAll()
//                        .requestMatchers("/database/validate").permitAll()
//
//                        .anyRequest().authenticated()
//                ).formLogin((form)->
//                        form.loginPage("/login").permitAll())
//                .logout((logout) -> logout.permitAll())
//                .rememberMe(r->r.key("uniqueAndSecret"));
//
//        http.csrf(c->c.disable());
//        http.httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails user1 = User.builder()
//                .username("admin").password(passwordEncoder().encode("admin@1"))
//                .roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user1);
//    }
//
//}