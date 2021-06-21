package com.racloop.learn.testing.mockito;

import com.racloop.learn.testing.mockito.Event;

public interface MessageProducer {

    public void send(Event event);
}
