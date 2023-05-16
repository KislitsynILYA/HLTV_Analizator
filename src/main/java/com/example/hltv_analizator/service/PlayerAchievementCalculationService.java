//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.PlayerAchievement;
//import com.example.hltv_analizator.entity.PointsAchievement;
//import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@Setter
//public class PlayerAchievementCalculationService {
//
//    @Autowired
//    private Repository dao;
//    @PostConstruct
//    void startPlayerAchievementCalculation(){
//        if (dao.playerAchievementIsEmpty()) {
//            dao.updatePlayerAchievement();
//        }
//        List<Integer> player_ids = dao.getAllPlayerAchievement_ids();
//        PointsAchievement pointsAchievement = dao.getPointsAchievement();
//        for (int i = 0; i < player_ids.size(); i++){
//            calculationPointsForAchievement(i, pointsAchievement);
//        }
//    }
//
//    void calculationPointsForAchievement(int i, PointsAchievement pointsAchievement){
//
//        int points_achievements = 0;
//
//        PlayerAchievement playerAchievement = dao.getNumbAchievements().get(i);
//        points_achievements = playerAchievement.getMvp_numb() * pointsAchievement.getMvp_points() +
//                playerAchievement.getEvp_numb() * pointsAchievement.getEvp_points() +
//                playerAchievement.getTier_1_1st_place_numb() * pointsAchievement.getTier_1_1st_place_points() +
//                playerAchievement.getTier_1_2nd_place_numb() * pointsAchievement.getTier_1_2nd_place_points() +
//                playerAchievement.getTier_1_3rd_place_numb() * pointsAchievement.getTier_1_3rd_place_points() +
//                playerAchievement.getTier_2_1st_place_numb() * pointsAchievement.getTier_2_1st_place_points() +
//                playerAchievement.getTier_2_2nd_place_numb() * pointsAchievement.getTier_2_2nd_place_points() +
//                playerAchievement.getTier_2_3rd_place_numb() * pointsAchievement.getTier_2_3rd_place_points();
//        playerAchievement.setPoints(points_achievements);
//        dao.savePlayerAchievement(playerAchievement);
//    }
//}
