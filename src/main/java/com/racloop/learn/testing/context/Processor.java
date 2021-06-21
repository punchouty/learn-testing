package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;

import java.util.Date;

public interface Processor {

    Event process(float temperature, Date time);
}
