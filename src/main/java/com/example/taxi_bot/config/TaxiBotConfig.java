package com.example.taxi_bot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@PropertySource(value = "classpath:messages.yaml", encoding = "UTF-8")
public class TaxiBotConfig {

}
