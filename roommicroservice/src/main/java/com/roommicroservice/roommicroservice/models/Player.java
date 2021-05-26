package com.roommicroservice.roommicroservice.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player", schema = "public")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "cardId")
    private Long cardId;

    public Player(Long id, Long userId, Long cardId) {
        this.id = id;
        this.userId = userId;
        this.cardId = cardId;
    }

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
