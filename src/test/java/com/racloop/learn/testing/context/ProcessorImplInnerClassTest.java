package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Calendar;
import java.util.Date;

@SpringJUnitConfig(classes = {ProcessorInnerConfigTest.Config.class})
class ProcessorInnerConfigTest {

    @Configuration
    public static class Config {

        @Bean
        public Enrich enrich() {
            return new EnrichDebugImpl();
        }

        @Bean
        public Processor processor(Enrich enrich) {
            return new ProcessorImpl(enrich);
        }
    }

    @Autowired
    private Processor processor;

    @Test
    void processFromConfig() {
        Event event = processor.process(25.5f, new Date());
        Assertions.assertEquals(Calendar.JANUARY, event.getMonthOfYear());
    }
}