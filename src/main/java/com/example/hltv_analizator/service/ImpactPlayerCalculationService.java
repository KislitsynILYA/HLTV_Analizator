//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.ImpactPlayer;
//import com.example.hltv_analizator.entity.Parameter;
//import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.DecimalFormat;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@Setter
//public class ImpactPlayerCalculationService {
//    @Autowired
//    private Repository dao;
//
//    @PostConstruct
//    void startImpactPlayersCalculation(){
//        List<Double> allImpact = dao.getAllImpact();
//        List<Integer> maps_numbs = dao.getAllMaps_numb();
//        List<Integer> player_ids = dao.getAllPlayer_ids();
//        DecimalFormat decimalFormat = new DecimalFormat("#.##");
//        short category_id = 6;
//        short tier_1_id = 1;
//        short tier_2_id = 2;
//        Parameter[] parameters = {dao.getParams(category_id, tier_1_id).get(0), dao.getParams(category_id, tier_2_id).get(0)};
//        List<Parameter> params = Arrays.asList(parameters);
//        Parameter parameters_tier_1 = params.get(0);
//        Parameter parameters_tier_2 = params.get(1);
//        for (int i = 0; i < allImpact.size(); i++){
//            Double points_tier_1 = -1.00;
//            Double coef_points_tier_1 = -1.00;
//            Double points_tier_2 = -1.00;
//            Double coef_points_tier_2 = -1.00;
//            if (i % 2 == 0) {
//                if (maps_numbs.get(i) < parameters_tier_1.getMin_maps_numb()) {
//                    points_tier_1 = 0.00;
//                    coef_points_tier_1 = 0.00;
//                }
//                else {
//                    points_tier_1 = (allImpact.get(i) - parameters_tier_1.getMin_stat())
//                            * parameters_tier_1.getPoints() / (parameters_tier_1.getMax_stat()
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
//                ImpactPlayer impactPlayer_tier_1 = dao.getByImpactPlayer_Id_tier1(player_ids.get(i));
//                impactPlayer_tier_1.setPoints(points_tier_1_res);
//                impactPlayer_tier_1.setCoef_points(coef_points_tier_1_res);
//                dao.saveImpactPlayer(impactPlayer_tier_1);
//            }
//            else {
//                if (maps_numbs.get(i) < parameters_tier_2.getMin_maps_numb()) {
//                    points_tier_2 = 0.00;
//                    coef_points_tier_2 = 0.00;
//                }
//                else {
//                    points_tier_2 = (allImpact.get(i) - parameters_tier_2.getMin_stat())
//                            * parameters_tier_2.getPoints() / (parameters_tier_2.getMax_stat()
//                            - parameters_tier_2.getMin_stat());
//
//                    if (maps_numbs.get(i) > parameters_tier_2.getHigh_mediana()) {
//                        coef_points_tier_2 = points_tier_2 * parameters_tier_2.getInc_coef();
//                    }
//                    else if (maps_numbs.get(i) <= parameters_tier_2.getLow_mediana()) {
//                        coef_points_tier_2 = points_tier_2 * parameters_tier_2.getDec_coef();
//                    }
//                    else {
//                        coef_points_tier_2 = points_tier_2 * parameters_tier_2.getCoef();
//                    }
//                }
//                Double points_tier_2_res = Double.parseDouble((decimalFormat.format(points_tier_2).replace(",",".")));
//                Double coef_points_tier_2_res = Double.parseDouble((decimalFormat.format(coef_points_tier_2).replace(",",".")));
//                ImpactPlayer impactPlayer_tier_2 = dao.getByImpactPlayer_Id_tier2(player_ids.get(i));
//                impactPlayer_tier_2.setPoints(points_tier_2_res);
//                impactPlayer_tier_2.setCoef_points(coef_points_tier_2_res);
//                dao.saveImpactPlayer(impactPlayer_tier_2);
//            }
//        }
//    }
//}
