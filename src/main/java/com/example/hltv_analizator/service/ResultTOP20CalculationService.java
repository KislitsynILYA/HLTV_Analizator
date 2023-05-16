package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Setter
public class ResultTOP20CalculationService {

    @Autowired
    private Repository dao;

    public List<Result> startResultTOP20Calculation(){
        List<Result> top_players = dao.getTop20Results();
        return top_players;
    }
}
