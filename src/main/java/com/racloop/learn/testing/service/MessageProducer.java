package com.racloop.learn.testing.service;

import com.racloop.learn.testing.model.Event;

public interface MessageProducer {

    public void send(Event event);
}
