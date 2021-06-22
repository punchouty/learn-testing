package com.racloop.learn.testing;

import com.racloop.learn.testing.api.BrainId;
import com.racloop.learn.testing.api.BrainIdRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BrainIdRepository brainIdRepository) {
        return args -> {
            BrainId brainId = new BrainId();
            brainId.setDomain("common");
            brainId.setId(1L);
            brainId.setKeyToken("key-token-1");
            brainIdRepository.save(brainId);
            brainId = new BrainId();
            brainId.setDomain("common");
            brainId.setId(2L);
            brainId.setKeyToken("key-token-2");
            brainIdRepository.save(brainId);
        };
    }

}
