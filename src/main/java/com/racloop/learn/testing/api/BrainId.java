package com.racloop.learn.testing.api;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BrainId {
    @Id
    private Long id;
    private String domain = null;
    private String name = null;
    private String keyToken = null;
}