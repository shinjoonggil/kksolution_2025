package com.kks.kksolution.config;

import com.kks.kksolution.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    @Value("${security.permitted.paths}")
    private String[] permittedPaths;
    private final AccountService accountService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        for (String permittedPath : permittedPaths) {
            log.info(permittedPath);
        }
        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(permittedPaths).permitAll()
                        .requestMatchers(
                                "/admin/**"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/account/signIn")
                                .usernameParameter("accountId")
                                .passwordParameter("accountPassword")
                                .successHandler((request, response, authentication) -> {
                                    response.sendRedirect("/account/signIn?error=none");
                                })
                                .failureHandler((request, response, exception) -> {
                                    log.info(exception.getMessage());
                                    response.sendRedirect("/account/signIn?error=");
                                })
//                        .successForwardUrl("/?SUCCESSFORWARDURL")
//                        .failureForwardUrl("/?FAILUREFORWARDURL")

                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/account/signOut")
                        .logoutSuccessUrl("/")
                        .permitAll()
                ).userDetailsService(accountService)
                .build();


    }
}
