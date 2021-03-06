package com.roommicroservice.roommicroservice.models;

import com.asi.lib.enums.ERoomState;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "player1id", referencedColumnName = "id")
    private Player player1;

    @OneToOne()
    @JoinColumn(name = "player2id", referencedColumnName = "id")
    private Player player2;

    @Enumerated(EnumType.STRING)
    private ERoomState state;

    @Column(name = "bet")
    private Long bet;

    @Column(name = "name")
    private String name;
}
