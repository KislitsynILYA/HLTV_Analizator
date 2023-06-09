package com.example.hltv_analizator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer player_id;

    @Column(name = "alias")
    private String alias;

    @Column(name = "hltv_id")
    private Integer hltv_id;
}
