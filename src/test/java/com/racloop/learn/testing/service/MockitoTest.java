package com.racloop.learn.testing.service;

import com.racloop.learn.testing.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mockito Interceptor
 */
@ExtendWith(MockitoExtension.class)
class MockitoTest {

    @InjectMocks
    private MessageConsumer messageConsumer;
    @Mock
    private MessageProducer messageProducer;
    @Mock
    private Filter filter;
    @Mock
    private Logger logger;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setTemperature(24.0f);
        event.setTimestamp(new Date());
    }

    @Test
    void onMessage() {
        messageConsumer.onMessage(event);
        verify(filter).filter(event);
    }

    @Test
    void onMessageTimes() {
        messageConsumer.onMessage(event);
        verify(filter, times(1)).filter(event);
    }

    @Test
    void onMessageAtLeast() {
        messageConsumer.onMessage(event);
        verify(filter, atMostOnce()).filter(event);
        verify(logger, atLeast(1)).log(any());
    }

    @Test
    void onMessageArgumentPositive() {
        when(filter.filter(any(Event.class))).thenReturn(Boolean.TRUE);

        messageConsumer.onMessage(event);

        verify(messageProducer, times(1)).send(any());
        verify(filter).filter(event);
        verify(logger, times(2)).log(any());
    }

    @Test
    void onMessageArgumentNegative() {
        when(filter.filter(any(Event.class))).thenReturn(Boolean.FALSE);

        messageConsumer.onMessage(event);

        verify(filter).filter(event);
        verify(messageProducer, never()).send(any());
        verify(logger, times(1)).log(any());
    }
}