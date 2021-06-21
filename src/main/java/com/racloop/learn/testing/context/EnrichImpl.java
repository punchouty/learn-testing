package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Primary
public class EnrichImpl implements Enrich {

    @Override
    public Event enrich(Event input) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(input.getTimestamp());
        input.setMonthOfYear(calendar.get(Calendar.MONTH));
        return input;
    }
}
