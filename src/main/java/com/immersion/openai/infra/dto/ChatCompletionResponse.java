package com.immersion.openai.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatCompletionResponse {

    private String id;
    private String object;
    private long created;
    private List<Choice> choices;


    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
        @JsonProperty("finish_reason")
        private String finishReason;

        // Getters and setters
    }
}
