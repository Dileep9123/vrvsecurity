package com.telusko.springsecurity.config;
                                import org.springframework.beans.factory.annotation.Autowired;


                                import org.springframework.context.annotation.Bean;
                                import org.springframework.context.annotation.Configuration;
                                import org.springframework.security.authentication.AuthenticationManager;
                                import org.springframework.security.authentication.AuthenticationProvider;
                                import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
                                import org.springframework.security.config.Customizer;
                                import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
                                import org.springframework.security.config.annotation.web.builders.HttpSecurity;
                                import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
                                import org.springframework.security.config.http.SessionCreationPolicy;
                                import org.springframework.security.core.userdetails.UserDetailsService;
                                import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
                                import org.springframework.security.crypto.password.NoOpPasswordEncoder;
                                import org.springframework.security.web.SecurityFilterChain;
                                import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
                                import static org.springframework.http.HttpMethod.DELETE;
                                import static org.springframework.http.HttpMethod.GET;
                                import static org.springframework.http.HttpMethod.POST;
                                import static org.springframework.http.HttpMethod.PUT;
                                
                                import static com.telusko.springsecurity.model.Permission.*;
                                import static com.telusko.springsecurity.model.Role.*;
                                
                                

                                @Configuration
                                @EnableWebSecurity
                                public class SecurityConfig {
                                	
                                	@Autowired
                                	private UserDetailsService userDetailsService;
                                    @Autowired
                                    JwtFilter jwtFilter;
                                    @Bean
                                    AuthenticationProvider authProvider() {
                                		
                                		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                                		
                                		provider.setUserDetailsService(userDetailsService);
                                		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
                                		return provider;
                                		
                                	}
                                    
                                    @Bean
                                    AuthenticationManager authenticationManager(AuthenticationConfiguration  config) throws Exception {
                                    	return config.getAuthenticationManager();
                                    }


                                    @Bean
                                    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                                		
//                                		http.csrf(customizer -> customizer.disable());
//                                		http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//                                		http.formLogin(Customizer.withDefaults());
//                                		http.httpBasic(Customizer.withDefaults());
//                                		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                                		return http.build();
                                		
                                		
                                		http.csrf(customizer -> customizer.disable())
                                		.authorizeHttpRequests(request -> request
                                				.requestMatchers("register" , "login", "hello", "about")
                                				.permitAll()
                                				.requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                                .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                                                .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                                                .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                                                .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                                                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())
                                				.anyRequest().authenticated())
                                		.httpBasic(Customizer.withDefaults())
                                		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                                         return http.build();




                                // Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new
                                // Customizer<CsrfConfigurer<HttpSecurity>>() {
                                //  
                                //  @Override public void customize(CsrfConfigurer<HttpSecurity> configurer) {
                                //  
                                //  configurer.disable(); } };
                                //  
                                //  Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
                                //  AuthorizationManagerRequestMatcherRegistry> custHttp = new
                                //  Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
                                //  AuthorizationManagerRequestMatcherRegistry>() {
                                //  
                                //  @Override public void customize(
                                //  AuthorizeHttpRequestsConfigurer<HttpSecurity>.
                                //  AuthorizationManagerRequestMatcherRegistry registry) {
                                //  registry.anyRequest().authenticated();
                                //
                                //  } };
                                // 
                                // http.authorizeHttpRequests(custHttp); http.csrf(custCsrf);
                                 
                                	}
                                	
                                	
//                                	@Bean
//                                	public UserDetailsService userDetailsService() {
//                                		
//                                		 UserDetails user = User
//                                				            .withDefaultPasswordEncoder()
//                                				            .username("navin")
//                                				            .password("n@123")
//                                				            .roles("USER")
//                                				            .build();
//                                		 UserDetails admin = User
//                                		            .withDefaultPasswordEncoder()
//                                		            .username("bhavana")
//                                		            .password("143")
//                                		            .roles("ADMIN")
//                                		            .build();
//                                		 
//                                		 return new InMemoryUserDetailsManager(user, admin);
//                                	}
                                }
