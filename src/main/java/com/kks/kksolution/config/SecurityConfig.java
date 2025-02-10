package com.kks.kksolution.config;

import com.kks.kksolution.service.AccountService;
import com.kks.kksolution.vo.common.MessageVO;
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
                                    request.getSession().setAttribute("message" , MessageVO.SUCCESS("SUCCSS!!"));
                                    log.info(authentication.getPrincipal().toString());
                                    log.info(authentication.getCredentials().toString());
                                    log.info(authentication.getDetails().toString());
                                    log.info(authentication.getAuthorities().toString());
                                    log.info(authentication.getName().toString());

                                    response.sendRedirect("/account/signIn?error=none");
                                })
                                .failureHandler((request, response, exception) -> {
                                    request.getSession().setAttribute("message" , MessageVO.ERROR("failureHandler!!"));
                                    response.sendRedirect("/account/signIn?error=fuck+you");
                                })
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer
                            .accessDeniedHandler((request, response, exception) -> {
                                request.getSession().setAttribute("message" , MessageVO.ERROR("accessDeniedHandler!!"));
                                response.sendRedirect("/");
                            })
                            .authenticationEntryPoint((request, response, exception) -> {
                                request.getSession().setAttribute("message" , MessageVO.ERROR("authenticationEntryPoint!!"));
                                response.sendRedirect("/account/signIn?error=entry");
                            });
                })
                .logout(logout -> logout
                        .logoutUrl("/account/signOut")
                        .logoutSuccessUrl("/")
                        .permitAll()
                ).userDetailsService(accountService)
                .build();


    }
}
