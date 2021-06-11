package com.racloop.learn.testing.service;

import com.racloop.learn.testing.model.Event;

public interface Filter {

    boolean filter(Event event);
}
