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
@Table(name = "tier_tournaments")

public class TierTournament implements Serializable {

    @Id
    @Column(name = "tier_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Short tier_id;

    @Column(name = "tier_name")
    private String tier_name;

    @Column(name = "coef_tier")
    private Short coef_tier;
}
