package com.racloop.learn.testing.service;

import com.racloop.learn.testing.model.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageConsumer {

    private Filter filter;
    private MessageProducer messageProducer;
    private Logger logger;

    public void onMessage(Event event) {
        logger.log("Before : " + event);
        boolean filtered = this.filter.filter(event);
        if(filtered) {
            messageProducer.send(event);
            logger.log("After : " + event);
        }
    }
}
