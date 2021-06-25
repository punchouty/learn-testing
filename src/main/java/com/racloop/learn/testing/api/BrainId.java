package com.racloop.learn.testing.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BrainId {
    @Id
    private Long id;
    @NotBlank
    private String domain;
    private String name;
    private String keyToken;
}