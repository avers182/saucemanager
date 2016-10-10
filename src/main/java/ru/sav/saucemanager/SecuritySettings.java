package ru.sav.saucemanager;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cas")
public class SecuritySettings {
    private String casService;
    private String loginUrl;
    private String logoutUrl;
    private String ticketValidator;
    private String roles;

    public String getCasService() {
        return casService;
    }

    public void setCasService(String casService) {
        this.casService = casService;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getTicketValidator() {
        return ticketValidator;
    }

    public void setTicketValidator(String ticketValidator) {
        this.ticketValidator = ticketValidator;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
