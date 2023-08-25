package com.immersion.openai.infra.client;

import com.immersion.openai.config.GptFeignConfiguration;
import com.immersion.openai.infra.dto.ChatCompletionResponse;
import com.immersion.openai.infra.dto.GptRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "GptClient", url = "${gpt.api.url}", configuration = GptFeignConfiguration.class)
public interface GptClient {

    @PostMapping("/v1/chat/completions")
    ChatCompletionResponse getGptCompletion(@RequestBody GptRequest req);
}
