package com.roommicroservice.roommicroservice.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room", schema = "public")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "player1Id", referencedColumnName = "id")
    private Player player1;

    @OneToOne()
    @JoinColumn(name = "player2Id", referencedColumnName = "id")
    private Player player2;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ERoomState state;

    @Column(name = "bet")
    private Long bet;

    public Room(Long id, Player player1, Player player2, ERoomState state, Long bet) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
        this.bet = bet;
    }

    public Long getBet() {
        return bet;
    }

    public void setBet(Long bet) {
        this.bet = bet;
    }

    public Room() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ERoomState getState() {
        return state;
    }

    public void setState(ERoomState state) {
        this.state = state;
    }
}
