package com.gmail.etauroginskaya.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan(basePackages = {
        "com.gmail.etauroginskaya.controller",
        "com.gmail.etauroginskaya.service",
        "com.gmail.etauroginskaya.repository"
})
@PropertySource("classpath:jdbc.properties")
public class AppConfig {
}
