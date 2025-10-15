package me.theowm.theopadel3.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/wigellpadel/listcourts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/wigellpadel/checkavailability/{courtId}/{date}").hasRole("USER")
                        .requestMatchers("/api/wigellpadel/v1/booking/bookcourt").hasRole("USER")
                        .requestMatchers("/api/wigellpadel/v1/mybookings").hasRole("USER")
                        .requestMatchers("/api/wigellpadel/v1/updatebooking").hasRole("USER")
                        .requestMatchers("/api/wigellpadel/v1/cancelbooking").hasRole("USER")
                        .requestMatchers("/api/wigellpadel/v1/listcanceled").hasRole("ADMIN")
                        .requestMatchers("/api/wigellpadel/v1/listupcoming").hasRole("ADMIN")
                        .requestMatchers("/api/wigellpadel/v1/listpast").hasRole("ADMIN")
                        .requestMatchers("/api/wigellpadel/v1/addcourt").hasRole("ADMIN")
                        .requestMatchers("/api/wigellpadel/v1/remcourt/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/wigellpadel/v1/updatecourt").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(csrf-> csrf.disable());
        return http.build();
    }

    //userDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails Anna = User
                .withUsername("anna.andersson@mail.se")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails Erik = User
                .withUsername("erik.eriksson@mail.se")
                .password("{noop}5678")
                .roles("USER")
                .build();

        UserDetails Maria = User
                .withUsername("maria.malm@mail.se")
                .password("{noop}9101")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, Anna, Erik, Maria);
    }

}
