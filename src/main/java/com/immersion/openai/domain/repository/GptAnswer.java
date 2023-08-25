package com.immersion.openai.domain.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@IdClass(GptAnswerPK.class)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GptAnswer {

    @Id
    private String puuid;

    @Id
    private String championName;

    @Column(length = 10000)
    String content;

    public void updateAnswer(String newAnswer) {
        content = newAnswer;
    }
}
