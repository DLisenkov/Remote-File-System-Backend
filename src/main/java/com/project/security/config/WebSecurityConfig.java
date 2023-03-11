package com.project.security.config;

import com.project.security.filters.CorsFilter;
import com.project.security.filters.ExceptionFilter;
import com.project.security.filters.TokenAuthFilter;
import com.project.security.provider.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Class extends the {@link WebSecurityConfigurerAdapter} and describes the configuration of the spring boot application
 */
@ComponentScan("com.project")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Field to access {@link TokenAuthFilter}
     */
    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    /**
     * Field to access {@link CorsFilter}
     */
    @Autowired
    private CorsFilter corsFilter;

    /**
     * Field to access {@link TokenAuthenticationProvider}
     */
    @Autowired
    private TokenAuthenticationProvider authenticationProvider;

    /**
     * Field to access {@link ExceptionFilter}
     */
    @Autowired
    private ExceptionFilter exceptionFilter;

    /**
     * Method sets filters, provider and permissions to requests
     *
     * @param http class object {@link HttpSecurity}
     * @throws Exception if there was an error while configuring
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(exceptionFilter, ChannelProcessingFilter.class)
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/files/**").authenticated()
                .antMatchers("/login", "/registration").permitAll();
        http.csrf().disable();
    }
}
