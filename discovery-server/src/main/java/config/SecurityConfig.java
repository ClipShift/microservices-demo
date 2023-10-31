package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.builder()
//                .username("bruh")
//                .password(passwordEncoder().encode("bruh"))
//                .roles("USER")
//                .build();
//        UserDetails eureka = User.builder()
//                .username("eureka")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails, eureka);
//    }
//
//    @Bean
//    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
//        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("/eureka/**")
//                    .permitAll()
//                    .anyExchange()
//                    .authenticated())
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable);
//        return serverHttpSecurity.build();
//    }
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${eureka.username}")
    private String username;

    @Value("${eureka.password}")
    private String password;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder()
                .username("bruh")
                .password(passwordEncoder().encode("bruh"))
                .roles("USER")
                .build();
        UserDetails eureka = User.builder()
                .username("eureka")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails, eureka);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }


}
