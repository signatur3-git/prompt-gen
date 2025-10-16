package com.signatur3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("prompt-gen")
@Validated
@Data
public class PromptGenProperties {

    @NestedConfigurationProperty
    Defaults defaults = new Defaults();

    @Validated
    @Data
    public static class Defaults {
        Integer promptCount = 1000;
        String rulebook = "com.signatur3::v7-showcase";
    }
}
