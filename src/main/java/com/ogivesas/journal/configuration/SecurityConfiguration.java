package com.ogivesas.journal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.ogivesas.journal.servicesImpl.UserDetailServiceImpl;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
	
	 private UserDetailServiceImpl userDetailServiceImpl;
	

	   @Bean
	    SecurityFilterChain web(HttpSecurity httpSecurity) throws Exception{
		 
		        httpSecurity                           
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/index","/prestations","/prestataires","/profile").permitAll()
						                                     .requestMatchers("/formInvoice","/monthInvoice")
						                                     .hasRole("ACCOUNTANT")
						                                     .requestMatchers("")
						                                     .hasRole("ADMIN")
						                                     .requestMatchers("/listUsers")
						                                     .hasRole("SUPER")
				                                             .anyRequest()
                                                             .authenticated()
				)
                .formLogin(formLogin -> formLogin
                		.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/index", true))
                .logout(logout -> logout.permitAll())
                .exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/403"))
			    .userDetailsService(userDetailServiceImpl) 
                .rememberMe(Customizer.withDefaults());
				
		 return httpSecurity.build();
	 }
}
