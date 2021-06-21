package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Calendar;
import java.util.Date;

@SpringJUnitConfig(classes = {ProcessorPackageScanTest.Config.class})
class ProcessorPackageScanTest {

    @Configuration
    @ComponentScan("com.racloop.learn.testing.context")
    public static class Config {

    }

    @Autowired
    private Processor processor;

    @Test
    void processFromConfig() {
        Event event = processor.process(25.5f, new Date());
        Assertions.assertEquals(Calendar.JUNE, event.getMonthOfYear());
    }
}