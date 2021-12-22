package com.ricardoseb.basicecommerce.security;


import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import reactor.core.publisher.Mono;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers;

/**
 * @author Ricardo Quiroga on 07-12-21
 */
@Configuration
public class SecurityConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {

        return new MapReactiveUserDetailsService(
                User.withDefaultPasswordEncoder()
                        .username("jim").password("demo").roles("ADMIN").build());
    }

    @Bean
    SecurityWebFilterChain myCustomSecurityPolicy(ServerHttpSecurity http) { // <1>
        return http //
                .authorizeExchange()
                .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .pathMatchers(HttpMethod.GET, "/api/auth").permitAll()
                .pathMatchers(HttpMethod.GET, "/").permitAll()
                .anyExchange().authenticated() // <3>
                .and()
                .formLogin()
                .requiresAuthenticationMatcher(pathMatchers(HttpMethod.GET, "/api/auth"))
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
                .authenticationFailureHandler(this::onAuthenticationError)
                .and()
                .csrf().disable()
                .build();

    }
    private Mono<Void> onAuthenticationError(WebFilterExchange exchange, AuthenticationException e) {
        exchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return Mono.empty();
    }

}
