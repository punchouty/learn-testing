package com.racloop.learn.testing.api;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
public class BrainId {
    @Id
    private Long id;
    @NotBlank
    private String domain = null;
    private String name = null;
    private String keyToken = null;
}