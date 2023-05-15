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
@Table(name = "points_achievements")
public class PointsAchievement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "achievement_id")
    private Integer achievement_id;

    @Column(name = "mvp_points")
    private Short mvp_points;

    @Column(name = "evp_points")
    private Short evp_points;

    @Column(name = "tier_1_1st_place_points")
    private Short tier_1_1st_place_points;

    @Column(name = "tier_1_2nd_place_points")
    private Short tier_1_2nd_place_points;

    @Column(name = "tier_1_3rd_place_points")
    private Short tier_1_3rd_place_points;

    @Column(name = "tier_2_1st_place_points")
    private Short tier_2_1st_place_points;

    @Column(name = "tier_2_2nd_place_points")
    private Short tier_2_2nd_place_points;

    @Column(name = "tier_2_3rd_place_points")
    private Short tier_2_3rd_place_points;
}
