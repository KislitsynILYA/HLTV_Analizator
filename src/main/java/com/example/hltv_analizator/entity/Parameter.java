package com.example.hltv_analizator.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "parameters")
public class Parameter implements Serializable {

    @Id
    @Column(name = "category_id")
    private Short category_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "tier_id", referencedColumnName = "tier_id")
    private TierTournament tier_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "points")
    private Integer points;

    @Column(name = "max_stat")
    private Double max_stat;

    @Column(name = "min_stat")
    private Double min_stat;

    @Column(name = "low_mediana")
    private Integer low_mediana;

    @Column(name = "high_mediana")
    private Integer high_mediana;

    @Column(name = "dec_coef")
    private Double dec_coef;

    @Column(name = "coef")
    private Double coef;

    @Column(name = "inc_coef")
    private Double inc_coef;

    @Column(name = "min_maps_numb")
    private Integer min_maps_numb;
}
