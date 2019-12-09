package com.example.drools.RuleEngine.Config;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class VelocityConfig {

    @Bean
    public VelocityEngine velocityEngine() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityEngine(properties);
    }
}
