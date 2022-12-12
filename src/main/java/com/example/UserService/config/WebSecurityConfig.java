package com.example.UserService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.undertow.server.handlers.RequestDumpingHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Value("${in-memory-auth.username}")
	private String USERNAME;
	
	@Value("${in-memory-auth.password}")
	private String PASSWORD;
	
	@Value("${in-memory-auth.role}")
	private String ROLE;
	
    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;
	
//	@Bean
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter
//          = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setIncludeHeaders(false);
//        filter.setAfterMessagePrefix("REQUEST DATA : ");
//        return filter;
//    }
//	@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.debug(webSecurityDebug);
//    }
    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addDeploymentInfoCustomizers(deploymentInfo -> 
               	deploymentInfo.addInitialHandlerChainWrapper(handler -> {
        	return new RequestDumpingHandler(handler);
        }));
            
        return factory;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.headers().frameOptions().sameOrigin(); //for h2
		
		http
//	        .csrf().disable()
//	        .cors().disable()
			.cors().and()
			.authorizeRequests()
//			.antMatchers("/auth/generate").permitAll()
			.antMatchers("/api/public").permitAll()
//			.antMatchers("/h2-console/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
//			.and().sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username(USERNAME)
            .password(PASSWORD)
            .roles(ROLE)
            .build();
        return new InMemoryUserDetailsManager(user);
    }
	
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
}
