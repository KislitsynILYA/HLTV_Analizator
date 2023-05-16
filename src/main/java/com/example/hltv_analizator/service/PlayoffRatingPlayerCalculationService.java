//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.Parameter;
//import com.example.hltv_analizator.entity.PlayoffRatingPlayer;
//import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@Setter
//public class PlayoffRatingPlayerCalculationService {
//
//    @Autowired
//    private Repository dao;
//    @PostConstruct
//    void startPlayoffRatingPlayersCalculation(){
//        if (dao.playoffRatingPlayerIsEmpty()) {
//            dao.updatePlayoffRatingPlayer();
//        }
//        List<Double> allPlayoffRating = dao.getAllPlayoffRating();
//        List<Integer> maps_numbs = dao.getAllMaps_numb_Playoff();
//        List<Integer> player_ids = dao.getAllPlayerPlayoff_ids();
//        short category_id = 1;
//        short tier_1_id = 1;
//        Parameter[] parameters = {dao.getParams(category_id, tier_1_id).get(0)};
//        List<Parameter> params = Arrays.asList(parameters);
//        Parameter parameters_tier_1 = params.get(0);
//        for (int i = 0; i < allPlayoffRating.size(); i++){
//            calculationPointsForPlayoffRating(maps_numbs.get(i), parameters_tier_1,
//                    allPlayoffRating.get(i), player_ids.get(i));
//        }
//    }
//
//    void calculationPointsForPlayoffRating(Integer maps_numb, Parameter parameters, Double playoffRating, Integer player_id) {
//
//        DecimalFormat decimalFormat = new DecimalFormat("#.##");
//        Double points = -1.00;
//        Double coef_points = -1.00;
//
//        if (maps_numb < parameters.getMin_maps_numb()) {
//            points = 0.00;
//            coef_points = 0.00;
//        }
//        else {
//            points = ((playoffRating - parameters.getMin_stat())
//                    * parameters.getPoints()) / (parameters.getMax_stat()
//                    - parameters.getMin_stat());
//
//            if (maps_numb > parameters.getHigh_mediana()) {
//                coef_points = points * parameters.getInc_coef();
//            }
//            else if (maps_numb <= parameters.getLow_mediana()) {
//                coef_points = points * parameters.getDec_coef();
//            }
//            else {
//                coef_points = points * parameters.getCoef();
//            }
//        }
//        Double points_res = Double.parseDouble((decimalFormat.format(points).replace(",",".")));
//        Double coef_points_res = Double.parseDouble((decimalFormat.format(coef_points).replace(",",".")));
//        PlayoffRatingPlayer playoffRatingPlayer = dao.getByPlayoffRatingPlayer_Id(player_id);
//        playoffRatingPlayer.setPoints(points_res);
//        playoffRatingPlayer.setCoef_points(coef_points_res);
//        dao.savePlayoffRatingPlayer(playoffRatingPlayer);
//    }
//}
