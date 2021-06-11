package com.racloop.learn.testing.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {

    private Date timestamp;
    private float temperature;
}
