package com.asi.lib.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String family;
    private String affinity;
    private Long hp;
    private Long energy;
    private Long attack;
    private Long defense;
    private Long price;
    private UserDTO user;

}
