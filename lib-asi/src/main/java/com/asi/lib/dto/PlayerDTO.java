package com.asi.lib.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PlayerDTO implements Serializable {

    private Long id;
    private Long userId;
    private Long cardId;

}
