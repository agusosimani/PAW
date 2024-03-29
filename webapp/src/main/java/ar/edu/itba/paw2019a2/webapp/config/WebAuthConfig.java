package ar.edu.itba.paw2019a2.webapp.config;

import ar.edu.itba.paw2019a2.webapp.auth.FoodifyUrlAuthentificationFailureHandler;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw2019a2.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .sessionManagement()
                .invalidSessionUrl("/")
                .and().authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/create").anonymous()
                    .antMatchers("/create/registration-confirm").anonymous()
                    .antMatchers("/create/resend-email-verification").anonymous()
                    //.antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").hasRole("VALIDATED")
                .and().formLogin()
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    .defaultSuccessUrl("/", true)
                    .failureHandler(new FoodifyUrlAuthentificationFailureHandler())
                    .loginPage("/login")
                .and().rememberMe()
                    .rememberMeParameter("j_rememberme")
                    .key(getKey())
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .and().exceptionHandling()
                    .accessDeniedPage("/403")
                .and().csrf().disable();
    }

    private String getKey() {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("rememberMe.key");
        try {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/css/**", "/resources/js/**", "/resources/img/**", "/403");
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
