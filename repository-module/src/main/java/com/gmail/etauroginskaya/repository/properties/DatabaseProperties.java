package com.gmail.etauroginskaya.repository.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseProperties {

    @Value("${database.url}")
    private String url;
    @Value("${database.username:}")
    private String username;
    @Value("${database.password:}")
    private String password;
    @Value("${database.driver.name}")
    private String driver;
    @Value("${database.script.path}")
    private String script;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public String getScript() {
        return script;
    }
}
