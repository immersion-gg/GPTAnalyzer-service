package com.immersion.openai.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GptAnswerPK implements Serializable {

    private String puuid;

    private String championName;

}
