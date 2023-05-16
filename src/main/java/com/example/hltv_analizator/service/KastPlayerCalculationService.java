//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.KastPlayer;
//import com.example.hltv_analizator.entity.Parameter;
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
//public class KastPlayerCalculationService {
//    @Autowired
//    private Repository dao;
//
//    @PostConstruct
//    void startKastPlayersCalculation(){
//        List<Double> allKast = dao.getAllKast();
//        List<Integer> maps_numbs = dao.getAllMaps_numb();
//        List<Integer> player_ids = dao.getAllPlayer_ids();
//        short category_id = 7;
//        short tier_1_id = 1;
//        short tier_2_id = 2;
//        Parameter[] parameters = {dao.getParams(category_id, tier_1_id).get(0), dao.getParams(category_id, tier_2_id).get(0)};
//        List<Parameter> params = Arrays.asList(parameters);
//        Parameter parameters_tier_1 = params.get(0);
//        Parameter parameters_tier_2 = params.get(1);
//        for (int i = 0; i < allKast.size(); i++){
//            if (i % 2 == 0) {
//                calculationPointsForKast(maps_numbs.get(i), parameters_tier_1,
//                        allKast.get(i), player_ids.get(i), tier_1_id);
//            }
//            else {
//                calculationPointsForKast(maps_numbs.get(i), parameters_tier_2,
//                        allKast.get(i), player_ids.get(i), tier_2_id);
//            }
//        }
//    }
//
//    void calculationPointsForKast(Integer maps_numb, Parameter parameters, Double kast, Integer player_id, short tier) {
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
//            points = ((kast - parameters.getMin_stat())
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
//        if (tier == 1) {
//            KastPlayer kastPlayer_tier_1 = dao.getByKastPlayer_Id_tier1(player_id);
//            kastPlayer_tier_1.setPoints(points_res);
//            kastPlayer_tier_1.setCoef_points(coef_points_res);
//            dao.saveKastPlayer(kastPlayer_tier_1);
//        }
//        else {
//            KastPlayer kastPlayer_tier_2 = dao.getByKastPlayer_Id_tier2(player_id);
//            kastPlayer_tier_2.setPoints(points_res);
//            kastPlayer_tier_2.setCoef_points(coef_points_res);
//            dao.saveKastPlayer(kastPlayer_tier_2);
//        }
//    }
//}
