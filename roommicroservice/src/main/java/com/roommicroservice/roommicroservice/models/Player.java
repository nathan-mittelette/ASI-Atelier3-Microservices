package com.roommicroservice.roommicroservice.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "cardId")
    private Long cardId;
}
