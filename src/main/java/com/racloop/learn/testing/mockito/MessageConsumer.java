package com.racloop.learn.testing.mockito;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageConsumer {

    private Filter filter;
    private MessageProducer messageProducer;
    private Logger logger;

    public void onMessage(Event event) {
        logger.log("Before : " + event);
//        event.setTemperature(event.getTemperature() + 1);
        boolean filtered = this.filter.filter(event);
        if(filtered) {
            messageProducer.send(event);
            logger.log("After : " + event);
        }
    }
}
