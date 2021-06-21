package com.racloop.learn.testing.mockito;

import com.racloop.learn.testing.mockito.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AdvanceMockito {

    @InjectMocks
    private MessageConsumer messageConsumer;
    @Mock
    private MessageProducer messageProducer;
    @Mock
    private Filter filter;
    @Mock
    private Logger logger;
    @Captor
    ArgumentCaptor<Event> argumentCaptor;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setTemperature(24.0f);
        event.setTimestamp(new Date());
    }

    @Test
    void exception() {
        doThrow(new RuntimeException("Crashed")).when(filter).filter(any(Event.class));

        Assertions.assertThrows(RuntimeException.class, () -> {
            messageConsumer.onMessage(event);
        });

        verify(filter).filter(event);
    }

    @Test
    void bddException() {
        // given
        willThrow(new RuntimeException("Crashed")).given(filter).filter(event);

        // when
        Assertions.assertThrows(RuntimeException.class, () -> {
            messageConsumer.onMessage(event);
        });

        // then
        then(filter).should().filter(event);
    }

    @Test
    void argumentCapture() {
        given(filter.filter(argumentCaptor.capture())).willReturn(Boolean.TRUE);

        messageConsumer.onMessage(event);

        assertThat(24.0f).isEqualTo(argumentCaptor.getValue().getTemperature());
    }

    @Test
    void inOrderTest() {
        given(filter.filter(argumentCaptor.capture())).willReturn(Boolean.TRUE);
        InOrder inOrder = inOrder(filter, logger, messageProducer);

        messageConsumer.onMessage(event);

        inOrder.verify(logger).log(any());
        inOrder.verify(filter).filter(any());
        inOrder.verify(messageProducer).send(any());
        inOrder.verify(logger).log(any());
    }

    @Test
    void testNoMoreInteractions() {
        given(filter.filter(argumentCaptor.capture())).willReturn(Boolean.FALSE);

        messageConsumer.onMessage(event);

        verify(logger).log(any());
        verify(filter).filter(event);
        verifyNoMoreInteractions(logger);
    }

    @Test
    void testNeverInteractions() {
        when(filter.filter(any(Event.class))).thenReturn(Boolean.FALSE);

        messageConsumer.onMessage(event);

        verify(filter).filter(event);
        verify(messageProducer, never()).send(any());
        verify(logger, times(1)).log(any());
    }
}
