package com.config;


import com.service.jwt.JwtAuthenticationFilter;
import com.service.jwt.JwtEntryPoint;
import com.service.jwt.appUser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JwtEntryPoint entryPoint;

    @Bean
    public JwtAuthenticationFilter jwtTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/register").permitAll()
                .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLES_ADMIN")
                .and().authorizeRequests().antMatchers("/trainer/**").hasAnyAuthority("ROLES_ADMIN","ROLES_TRAINER")
                .and().authorizeRequests().antMatchers("/player/**").hasAnyAuthority("ROLES_ADMIN","ROLES_TRAINER","ROLES_PLAYER")
                .and().csrf().disable();


////        chưa phân quyền:
//        http.authorizeRequests().antMatchers("/login", "/register").permitAll()
//                .and().authorizeRequests().antMatchers("/admin/**").permitAll()
//                .and().authorizeRequests().antMatchers("/trainer/**").permitAll()
//                .and().authorizeRequests().antMatchers("/player/**").permitAll()
//                .and().csrf().disable();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling();
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        http.cors().configurationSource(c -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.applyPermitDefaultValues();
            configuration.addAllowedOriginPattern("*");
//            configuration.addAllowedMethod(CorsConfiguration.ALL);
            configuration.addAllowedMethod(HttpMethod.DELETE);
            configuration.addAllowedMethod(HttpMethod.GET);
            configuration.addAllowedMethod(HttpMethod.POST);
            configuration.addAllowedMethod(HttpMethod.PUT);
            configuration.addAllowedMethod(HttpMethod.HEAD);
            return configuration;
        });
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
