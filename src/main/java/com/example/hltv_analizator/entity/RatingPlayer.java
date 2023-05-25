package com.example.hltv_analizator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "rating_players")
public class RatingPlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "tier_id", referencedColumnName = "tier_id")
    private TierTournament tier_id;

    @Column(name = "maps_numb")
    private Integer maps_numb;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "points")
    private Double points;

    @Column(name = "coef_points")
    private Double coef_points;
}
