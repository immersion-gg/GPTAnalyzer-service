package com.immersion.openai.infra.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GptRequest(
        String model,
        List<Message> messages
) {
}
