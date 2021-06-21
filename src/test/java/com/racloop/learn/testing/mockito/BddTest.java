package com.racloop.learn.testing.mockito;

import com.racloop.learn.testing.mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BddTest {

    @InjectMocks
    private MessageConsumer messageConsumer;
    @Mock
    private MessageProducer messageProducer;
    @Mock
    private Filter filter;
    @Mock
    private Logger logger;

    private Event event;

    void testStructure() {
        // given

        // when

        // then
    }

    @Test
    void onMessagePositive() {
        // given
        event = new Event();
        event.setTemperature(24.0f);
        event.setTimestamp(new Date());
        //when(filter.filter(any(Event.class))).thenReturn(Boolean.TRUE);
        given(filter.filter(any(Event.class))).willReturn(Boolean.TRUE);

        // when
        messageConsumer.onMessage(event);

        // then
        // verify(filter).filter(event);
        then(filter).should().filter(event);
        // verify(messageProducer, never()).send(any());
        then(messageProducer).should().send(event);
        // verify(logger, times(1)).log(any());
        then(logger).should(times(2)).log(any());
    }

    @Test
    void onMessageNegative() {
        // given
        event = new Event();
        event.setTemperature(24.0f);
        event.setTimestamp(new Date());
        //when(filter.filter(any(Event.class))).thenReturn(Boolean.FALSE);
        given(filter.filter(any(Event.class))).willReturn(Boolean.FALSE);

        // when
        messageConsumer.onMessage(event);

        // then
        // verify(filter).filter(event);
        then(filter).should().filter(event);
        // verify(messageProducer, never()).send(any());
        then(messageProducer).shouldHaveNoInteractions();
        // verify(logger, times(1)).log(any());
        then(logger).should().log(any());
    }
}
