package kg.attractor.microgram.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)  )
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
//                        .successHandler()

                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher( "/auth/logout"))
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest

                        .requestMatchers(HttpMethod.POST, "/resumes").hasAuthority("Applicant")
                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasAuthority("Applicant")
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasAuthority("Applicant")
                        .requestMatchers("/responses/**").hasAuthority("Applicant")
                        .requestMatchers("/education/**").hasAuthority("Applicant")
                        .requestMatchers( "/workExp/**").hasAuthority("Applicant")
                        .requestMatchers("/contactInfo/**").hasAuthority("Applicant")

                        .requestMatchers(HttpMethod.POST, "/vacancies").hasAuthority("Employer")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasAuthority("Employer")
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasAuthority("Employer")
                        .requestMatchers(HttpMethod.GET, "/users/responded/**").hasAuthority("Employer")

//                        .requestMatchers(HttpMethod.GET, "/users/companies").hasAuthority("Applicant")
                        .requestMatchers(HttpMethod.PUT, "/users/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/users/avatar").authenticated()
                        .requestMatchers(HttpMethod.GET, "/resumes/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/register").permitAll()
                                .requestMatchers("/auth/login/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                                .requestMatchers(HttpMethod.GET, "/auth/forgot_password/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/forgot_password/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/auth/reset_password/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/reset_password/**").permitAll()
                                .requestMatchers("/changeLanguage").permitAll()

                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vacancies/**").permitAll()

                        .anyRequest().authenticated()

                );

        return http.build();
    }
}
