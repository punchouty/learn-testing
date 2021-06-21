package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProcessorImpl implements Processor {

    private final Enrich enrich;

    public ProcessorImpl(Enrich enrich) {
        this.enrich = enrich;
    }

    @Override
    public Event process(float temperature, Date time) {
        Event event = new Event();
        event.setTemperature(temperature);
        event.setTimestamp(time);
        return this.enrich.enrich(event);
    }
}
