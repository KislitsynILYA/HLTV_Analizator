package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.AdrPlayer;
import com.example.hltv_analizator.entity.Parameter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Setter
public class AdrPlayerCalculationService {

    @Autowired
    private Repository dao;

    void startAdrPlayersCalculation(){
        List<Double> allAdr = dao.getAllAdr();
        List<Integer> maps_numbs = dao.getAllMaps_numb();
        List<Integer> player_ids = dao.getAllPlayer_ids();
        short category_id = 5;
        short tier_1_id = 1;
        short tier_2_id = 2;
        Parameter[] parameters = {dao.getParams(category_id, tier_1_id).get(0), dao.getParams(category_id, tier_2_id).get(0)};
        List<Parameter> params = Arrays.asList(parameters);
        Parameter parameters_tier_1 = params.get(0);
        Parameter parameters_tier_2 = params.get(1);
        for (int i = 0; i < allAdr.size(); i++){
            if (i % 2 == 0) {
                calculationPointsForAdr(maps_numbs.get(i), parameters_tier_1,
                        allAdr.get(i), player_ids.get(i), tier_1_id);
            }
            else {
                calculationPointsForAdr(maps_numbs.get(i), parameters_tier_2,
                        allAdr.get(i), player_ids.get(i), tier_2_id);
            }
        }
    }

    void calculationPointsForAdr(Integer maps_numb, Parameter parameters, Double adr, Integer player_id, short tier) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double points;
        Double coef_points;

        if (maps_numb < parameters.getMin_maps_numb()) {
            points = 0.00;
            coef_points = 0.00;
        }
        else {
            points = ((adr - parameters.getMin_stat())
                    * parameters.getPoints()) / (parameters.getMax_stat()
                    - parameters.getMin_stat());

            if (maps_numb > parameters.getHigh_mediana()) {
                coef_points = points * parameters.getInc_coef();
            }
            else if (maps_numb <= parameters.getLow_mediana()) {
                coef_points = points * parameters.getDec_coef();
            }
            else {
                coef_points = points * parameters.getCoef();
            }
        }
        Double points_res = Double.parseDouble((decimalFormat.format(points).replace(",",".")));
        Double coef_points_res = Double.parseDouble((decimalFormat.format(coef_points).replace(",",".")));
        if (tier == 1) {
            AdrPlayer adrPlayer_tier_1 = dao.getByAdrPlayer_Id_tier1(player_id);
            adrPlayer_tier_1.setPoints(points_res);
            adrPlayer_tier_1.setCoef_points(coef_points_res);
            dao.saveAdrPlayer(adrPlayer_tier_1);
        }
        else {
            AdrPlayer adrPlayer_tier_2 = dao.getByAdrPlayer_Id_tier2(player_id);
            adrPlayer_tier_2.setPoints(points_res);
            adrPlayer_tier_2.setCoef_points(coef_points_res);
            dao.saveAdrPlayer(adrPlayer_tier_2);
        }
    }
}
