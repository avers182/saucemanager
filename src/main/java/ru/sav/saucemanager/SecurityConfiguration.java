package ru.sav.saucemanager;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.userdetails.GrantedAuthorityFromAssertionAttributesUserDetailsService;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    SecuritySettings securitySettings;

    @Bean
    public ServiceProperties casServiceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setSendRenew(false);
        serviceProperties.setService(securitySettings.getCasService());
        return serviceProperties;
    }

    @Bean
    public TicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(securitySettings.getTicketValidator());
//        return new ArrayAwareCas20ServiceTicketValidator(ticketValidator);
    }

    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
        GrantedAuthorityFromAssertionAttributesUserDetailsService userDetailsService = new GrantedAuthorityFromAssertionAttributesUserDetailsService(new String[]{"roles"});
        userDetailsService.setConvertToUpperCase(false);
        return userDetailsService;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setServiceProperties(casServiceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
        casAuthenticationProvider.setKey("casAuthProvider");
        return casAuthenticationProvider;
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setServiceProperties(casServiceProperties());
        entryPoint.setLoginUrl(securitySettings.getLoginUrl());
        return  entryPoint;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        return new SingleSignOutFilter();
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/j_spring_cas_security_check");
        return filter;
    }

    @Bean
    public LogoutFilter casTotalLogoutFilter() {
        LogoutFilter filter = new LogoutFilter(securitySettings.getLogoutUrl(), new SecurityContextLogoutHandler());
        filter.setFilterProcessesUrl("/logout/cas");
        return filter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint()).accessDeniedPage("/denied.jsp");
//        http.authorizeRequests().antMatchers("/**").hasAnyRole("viewLMS");
        http.authorizeRequests().antMatchers("/cas-logout.jsp").permitAll().antMatchers("/**").authenticated();
        http.logout().logoutSuccessUrl("/cas-logout.jsp");
        http.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
                .addFilter(casAuthenticationFilter())
                .addFilterBefore(casTotalLogoutFilter(), LogoutFilter.class);
    }

}
