package com.greenwayshop.learning.security;

import com.greenwayshop.learning.exceptions.JSONCreationFailException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final DataSource dataSource;

    SecurityConfig( DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder())
                .usersByUsernameQuery("select username, password, active from user_details where username=?")
                .authoritiesByUsernameQuery("select u.username, ua.authorities from user_details u join user_authorities ua on u.id = ua.user_id where u.username = ?")
                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel().anyRequest().requiresSecure();
        http
                .headers()
                .httpStrictTransportSecurity()

                .and()
                .xssProtection()

                .and()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/auth", "/", "/about", "/cart/*", "/order", "/query", "/registration")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/me", "/authenticated/*", "/userInfoForSession")
                .hasAuthority("ROLE_CUSTOMER")
                .and()
                .httpBasic()

                .and()
                .formLogin()
                .permitAll()
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .loginProcessingUrl("/auth")
                .and()
                .logout()
                .logoutUrl("/dropAuth")
                .and()

                .csrf()
                .disable();
    }
    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                JSONObject jsonAnswer = new JSONObject();
                log.info("Authentication request for user: " + authentication.getName());
                log.info("Authentication successful!");
                try {
                    jsonAnswer.put("status", "ok")
                            .put("code", 200)
                            .put("details", "Successful login");
                } catch (JSONException ex) {
                    throw new JSONCreationFailException();
                }
                String stringAnswer = jsonAnswer.toString();
                log.info("RETURNING JSON ANSWER: " + stringAnswer);
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().append(stringAnswer);
                httpServletResponse.setStatus(200);
            }
        };
    }
    private AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws ServletException, IOException {
                log.info("Access denied with exception: " + e.toString());
                httpServletResponse.getWriter().append("");
                httpServletResponse.setStatus(403);
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            JSONObject jsonAnswer = new JSONObject();
            try {
                jsonAnswer.put("status", "denied")
                        .put("code", 404)
                        .put("details", "USER NOT FOUND");
            } catch (JSONException ex) {
                throw new JSONCreationFailException();
            }
            String stringAnswer = jsonAnswer.toString();
            log.info("RETURNING JSON ANSWER: " + stringAnswer);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().append(stringAnswer);
            httpServletResponse.setStatus(404);
            log.info("Authentication failure");
            log.info(e.toString());
        };
    }
}
