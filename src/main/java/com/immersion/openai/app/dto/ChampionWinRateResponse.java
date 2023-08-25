package com.immersion.openai.app.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import java.util.Map;

@Data
public class ChampionWinRateResponse {
    private String puuid;
    private String championName;

    private Map<String, ChampionWinRateDto> winRateMap;

    @JsonProperty("winRateMap")
    private void deserializeWinRateMap(String winRateMapString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.winRateMap = objectMapper.readValue(winRateMapString, Map.class);
    }
}
