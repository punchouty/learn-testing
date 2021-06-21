package com.racloop.learn.testing.context;

import com.racloop.learn.testing.mockito.Event;

public interface Enrich {

    Event enrich(Event input);
}
