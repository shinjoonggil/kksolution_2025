package com.kks.kksolution.config;

import com.kks.kksolution.service.AccountService;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    @Value("${security.permitted.paths}")
    private String[] permittedPaths;
    private final AccountService accountService;
    private final static String redirectUrlSessionKey = "redirectURL";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/upload/**")) // 파일 업로드 관련 요청 제외

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
                            HttpSession session = request.getSession();
                            session.setAttribute("message", MessageVO.SUCCESS("account.success.signin"));
                            Object redirectUrl = session.getAttribute(redirectUrlSessionKey);
                            if (redirectUrl == null) {
                                response.sendRedirect("/");
                            } else {
                                response.sendRedirect(redirectUrl.toString());
                            }
                        })
                        .failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("message", MessageVO.ERROR("failureHandler!!"));
                            response.sendRedirect("/account/signIn?error=fuck+you");
                        })

                        .permitAll()
                )
                .rememberMe(httpSecurityRememberMeConfigurer -> {
                    httpSecurityRememberMeConfigurer.rememberMeParameter("remember").tokenValiditySeconds(7 * 24 * 60 * 60);
                })
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer
                            .accessDeniedHandler((request, response, exception) -> {
                                log.info(request.getRequestURI());
                                request.getSession().setAttribute("message", MessageVO.ERROR("error.role"));
                                response.sendRedirect("/");
                            })
                            .authenticationEntryPoint((request, response, exception) -> {
                                HttpSession session = request.getSession();
                                session.setAttribute(redirectUrlSessionKey, request.getRequestURI());
                                session.setAttribute("message", MessageVO.ERROR("error.entry"));
                                response.sendRedirect("/account/signIn");
                            });
                })
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/account/signOut"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember")
                        .permitAll()
                ).userDetailsService(accountService)
                .build();


    }
}
