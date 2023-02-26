package laundry.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private LaundryAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    @Bean
	public JwtAuthenticationFilter tokenAuthenticationFilter() {
    	JwtAuthenticationFilter tokenAuthenticationFilter = new JwtAuthenticationFilter();
		return tokenAuthenticationFilter;
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.authorizeRequests()
					.antMatchers("/api/laundry/auth/login").permitAll()
					.antMatchers(HttpMethod.POST, "/api/laundry/users").permitAll()
					.antMatchers("/api/laundry/stores/**").hasAuthority("STORE_OWNER")
				.anyRequest().authenticated().and()
				.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
	}
}
