package com.d2c.ethereum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.d2c.ethereum.EtheTemplate;

@Configuration
public class EtheConfig {

    @Bean
    EtheTemplate etheTemplate() throws Exception {
        EtheTemplate etheTemplate = new EtheTemplate();
        etheTemplate.init();
        
//        Executors.newSingleThreadExecutor().submit(etheTemplate::init);

        return etheTemplate;
    }
}
