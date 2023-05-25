//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.AdrPlayer;
//import com.example.hltv_analizator.entity.DprPlayer;
//import com.example.hltv_analizator.entity.ImpactPlayer;
//import com.example.hltv_analizator.entity.KastPlayer;
//import com.example.hltv_analizator.entity.KprPlayer;
//import com.example.hltv_analizator.entity.PlayerAchievement;
//import com.example.hltv_analizator.entity.PlayoffRatingPlayer;
//import com.example.hltv_analizator.entity.RatingPlayer;
//import com.example.hltv_analizator.entity.Result;
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
//public class ResultCalculationService {
//
//    @Autowired
//    private Repository dao;
//    @PostConstruct
//    void startResultCalculation(){
//        List<Integer> player_ids = dao.getAllResult_ids();
//        List<Short> coef_tiers = dao.getAllCoef_Tier();
//        for (int i = 0; i < player_ids.size(); i++){
//            calculationFullPointsForResult(player_ids.get(i), coef_tiers);
//        }
//    }
//
//    void calculationFullPointsForResult(Integer player_id, List<Short> coef_tiers){
//
//        Double full_points = 0.0;
//
//        RatingPlayer ratingPlayer_tier1 = dao.getByRatingPlayer_Id_tier1(player_id);
//        RatingPlayer ratingPlayer_tier2 = dao.getByRatingPlayer_Id_tier2(player_id);
//        AdrPlayer adrPlayer_tier1 = dao.getByAdrPlayer_Id_tier1(player_id);
//        AdrPlayer adrPlayer_tier2 = dao.getByAdrPlayer_Id_tier2(player_id);
//        DprPlayer dprPlayer_tier1 = dao.getByDprPlayer_Id_tier1(player_id);
//        DprPlayer dprPlayer_tier2 = dao.getByDprPlayer_Id_tier2(player_id);
//        ImpactPlayer impactPlayer_tier1 = dao.getByImpactPlayer_Id_tier1(player_id);
//        ImpactPlayer impactPlayer_tier2 = dao.getByImpactPlayer_Id_tier2(player_id);
//        KastPlayer kastPlayer_tier1 = dao.getByKastPlayer_Id_tier1(player_id);
//        KastPlayer kastPlayer_tier2 = dao.getByKastPlayer_Id_tier2(player_id);
//        KprPlayer kprPlayer_tier1 = dao.getByKprPlayer_Id_tier1(player_id);
//        KprPlayer kprPlayer_tier2 = dao.getByKprPlayer_Id_tier2(player_id);
//        PlayerAchievement playerAchievement = dao.getByPlayerAchievement_Id(player_id);
//        PlayoffRatingPlayer playoffRatingPlayer = dao.getByPlayoffRatingPlayer_Id(player_id);
//
//        full_points = ratingPlayer_tier1.getCoef_points() * coef_tiers.get(0) +
//                ratingPlayer_tier2.getCoef_points() * coef_tiers.get(1) +
//                adrPlayer_tier1.getCoef_points() * coef_tiers.get(0) +
//                adrPlayer_tier2.getCoef_points() * coef_tiers.get(1) +
//                dprPlayer_tier1.getCoef_points() * coef_tiers.get(0) +
//                dprPlayer_tier2.getCoef_points() * coef_tiers.get(1) +
//                impactPlayer_tier1.getCoef_points() * coef_tiers.get(0) +
//                impactPlayer_tier2.getCoef_points() * coef_tiers.get(1) +
//                kastPlayer_tier1.getCoef_points() * coef_tiers.get(0) +
//                kastPlayer_tier2.getCoef_points() * coef_tiers.get(1) +
//                kprPlayer_tier1.getCoef_points() * coef_tiers.get(0) +
//                kprPlayer_tier2.getCoef_points() * coef_tiers.get(1) +
//                playoffRatingPlayer.getCoef_points() * coef_tiers.get(0) +
//                playerAchievement.getPoints();
//
//        Result result_player = dao.getByResultPlayer_Id(player_id);
//        result_player.setFull_points(full_points);
//        dao.saveResult(result_player);
//    }
//
//    public List<Result> resultTOP20Calculation(){
//        return dao.getTop20Results();
//    }
//}
