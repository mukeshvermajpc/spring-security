package com.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().authenticated();
                }).formLogin(
                        form -> form.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/welcome")
                                .permitAll()
                ) //formLogin(Customizer.withDefaults())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());
        ; //.httpBasic(Customizer.withDefaults())
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails rahul = User.builder()
                            .username("rahul")
                            .password(passwordEncoder()
                                    .encode("admin"))
                            .roles("ADMIN").build();
        UserDetails anil = User.builder()
                .username("anil")
                .password(passwordEncoder()
                        .encode("admin"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(rahul,anil);
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
