package com.example.hltv_analizator.controller;

import com.example.hltv_analizator.entity.Result;
import com.example.hltv_analizator.service.ResultCalculationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("calculate")
@AllArgsConstructor
public class Controller {

    @Autowired
    private ResultCalculationService calculationTop20Service;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResultWrapper {
        private Result result;
        private String top_place;
    }

    @GetMapping("top20")
    @ResponseBody
    public ResponseEntity<List<ResultWrapper>> getTop20() {
        List<Result> results = calculationTop20Service.resultTOP20Calculation();
        List<ResultWrapper> wrappedResults = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            ResultWrapper wrapper = new ResultWrapper(result, "TOP-"+ (i + 1));
            wrappedResults.add(wrapper);
        }
        return new ResponseEntity<>(wrappedResults, HttpStatus.OK);
    }
}

