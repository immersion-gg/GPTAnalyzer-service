package com.immersion.openai.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immersion.openai.app.dto.ChampionWinRateResponse;
import com.immersion.openai.config.KafkaProperties;
import com.immersion.openai.domain.repository.GptAnswer;
import com.immersion.openai.domain.repository.GptAnswerPK;
import com.immersion.openai.domain.repository.GptAnswerRepository;
import com.immersion.openai.infra.client.GptClient;
import com.immersion.openai.infra.dto.ChatCompletionResponse;
import com.immersion.openai.infra.dto.GptRequest;
import com.immersion.openai.infra.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GptAnalyzeService {

    private final GptClient gptClient;
    private final ObjectMapper objectMapper;
    private final GptAnswerRepository gptAnswerRepository;


    @KafkaListener(topics = {"chatGpt"}, groupId = KafkaProperties.CONSUMER_GROUP_ID)
    public void consumerMessage(String message) throws JsonProcessingException {
        ChampionWinRateResponse chatCompletionResponse = objectMapper.readValue(message, ChampionWinRateResponse.class);
        String completion = getGptCompletion(chatCompletionResponse);

        Optional<GptAnswer> answer = gptAnswerRepository.findById(new GptAnswerPK(chatCompletionResponse.getPuuid(), chatCompletionResponse.getChampionName()));
        answer.ifPresent(gptAnswer -> gptAnswer.updateAnswer(completion));
        gptAnswerRepository.save(GptAnswer.of(chatCompletionResponse.getPuuid(), chatCompletionResponse.getChampionName(), completion));
    }

    private String getGptCompletion(ChampionWinRateResponse data) throws JsonProcessingException {

        String jsonData = objectMapper.writeValueAsString(data);

        String userMessage = String.format("다음 데이터는 리그오브레전드 어떤 유저가 %s를 플레이했을때의 상대 챔피언별 승률 통계입니다. %s 해당 통계를 보고 게임 전문가인 당신이 해당 챔피언들의 특성(근거리, 원거리, AD, AP 등등)을 고려하여 분석하고 총 평가를 말해주시기 바랍니다.",
                data.getChampionName(), jsonData);

        Message system = Message.of("system", "The best analyst in the game, League of Legends");
        Message user = Message.of("user", userMessage);

        GptRequest request = GptRequest.builder()
                .model("gpt-4")
                .messages(List.of(system, user))
                .build();

        ChatCompletionResponse gptCompletion = gptClient.getGptCompletion(request);
        String gptContent = gptCompletion.getChoices().get(0).getMessage().content();

        log.info("gpt - {}", gptContent);

        return gptContent;
    }

}
