package com.tlimskech.marketplace.config;

import com.cloudinary.Cloudinary;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Log4j2
public class AppConfig {

    private @Value("${cloudinary.apikey}") String key;
    private @Value("${cloudinary.apisecret}") String secret;
    @Value("${cloudinary.cloudname}")
    private String cloud;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(60000)
                .setReadTimeout(60000)
                .build();
    }

    @Bean
    @Profile("prod")
    public Cloudinary cloudinaryConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloud);
        config.put("api_key", key);
        config.put("api_secret", secret);
        return new Cloudinary(config);
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        log.info("Created FreeMarkerConfigurer Bean ==========");
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
        TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/templates/mailtemplates/");
        configuration.setTemplateLoader(templateLoader);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }
}
