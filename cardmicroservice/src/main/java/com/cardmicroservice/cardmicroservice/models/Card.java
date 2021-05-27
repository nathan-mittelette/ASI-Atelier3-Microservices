package com.cardmicroservice.cardmicroservice.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card", schema = "public")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imageurl")
    private String imageUrl;

    @Column(name = "family")
    private String family;

    @Column(name = "affinity")
    private String affinity;

    @Column(name = "hp")
    private Long hp;

    @Column(name = "energy")
    private Long energy;

    @Column(name = "attack")
    private Long attack;

    @Column(name = "defense")
    private Long defense;

    @Column(name = "price")
    private Long price;

    @Column(name = "userid")
    private Long userid;
}
