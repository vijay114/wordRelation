package com.venly.wordRelation.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * DTO for analogy entity class
 *
 * @author Vijay
 */
@Getter
@Setter
@Builder
public class AnalogyDTO {

    private Long id;
    @NotEmpty
    private String word1;
    @NotEmpty
    private String word2;
    @NotEmpty
    private String relation;

}
