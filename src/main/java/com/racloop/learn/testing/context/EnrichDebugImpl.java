package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Profile("debug")
public class EnrichDebugImpl implements Enrich {
    @Override
    public Event enrich(Event input) {
        input.setMonthOfYear(Calendar.JANUARY);
        return input;
    }
}
