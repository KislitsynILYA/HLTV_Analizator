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
//        DecimalFormat decimalFormat = new DecimalFormat("#.##");
//        short category_id = 1;
//        short tier_1_id = 1;
//        Parameter[] parameters = {dao.getParams(category_id, tier_1_id).get(0)};
//        List<Parameter> params = Arrays.asList(parameters);
//        Parameter parameters_tier_1 = params.get(0);
//        for (int i = 0; i < allPlayoffRating.size(); i++){
//            Double points_tier_1 = -1.00;
//            Double coef_points_tier_1 = -1.00;
//                if (maps_numbs.get(i) < parameters_tier_1.getMin_maps_numb()) {
//                    points_tier_1 = 0.00;
//                    coef_points_tier_1 = 0.00;
//                }
//                else {
//                    points_tier_1 = ((allPlayoffRating.get(i) - parameters_tier_1.getMin_stat())
//                            * parameters_tier_1.getPoints()) / (parameters_tier_1.getMax_stat()
//                            - parameters_tier_1.getMin_stat());
//
//                    if (maps_numbs.get(i) > parameters_tier_1.getHigh_mediana()) {
//                        coef_points_tier_1 = points_tier_1 * parameters_tier_1.getInc_coef();
//                    }
//                    else if (maps_numbs.get(i) <= parameters_tier_1.getLow_mediana()) {
//                        coef_points_tier_1 = points_tier_1 * parameters_tier_1.getDec_coef();
//                    }
//                    else {
//                        coef_points_tier_1 = points_tier_1 * parameters_tier_1.getCoef();
//                    }
//                }
//                Double points_tier_1_res = Double.parseDouble((decimalFormat.format(points_tier_1).replace(",",".")));
//                Double coef_points_tier_1_res = Double.parseDouble((decimalFormat.format(coef_points_tier_1).replace(",",".")));
//                PlayoffRatingPlayer playoffRatingPlayer = dao.getByPlayoffRatingPlayer_Id(player_ids.get(i));
//                playoffRatingPlayer.setPoints(points_tier_1_res);
//                playoffRatingPlayer.setCoef_points(coef_points_tier_1_res);
//                dao.savePlayoffRatingPlayer(playoffRatingPlayer);
//        }
//    }
//}
