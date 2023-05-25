package com.example.hltv_analizator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "players_achievements")
public class PlayerAchievement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player_id;

    @Column(name = "mvp_numb")
    private Short mvp_numb;

    @Column(name = "evp_numb")
    private Short evp_numb;

    @Column(name = "tier_1_1st_place_numb")
    private Short tier_1_1st_place_numb;

    @Column(name = "tier_1_2nd_place_numb")
    private Short tier_1_2nd_place_numb;

    @Column(name = "tier_1_3rd_place_numb")
    private Short tier_1_3rd_place_numb;

    @Column(name = "tier_2_1st_place_numb")
    private Short tier_2_1st_place_numb;

    @Column(name = "tier_2_2nd_place_numb")
    private Short tier_2_2nd_place_numb;

    @Column(name = "tier_2_3rd_place_numb")
    private Short tier_2_3rd_place_numb;

    @Column(name = "points")
    private Integer points;
}
