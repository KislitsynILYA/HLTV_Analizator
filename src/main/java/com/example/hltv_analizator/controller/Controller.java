package com.example.hltv_analizator.controller;

import com.example.hltv_analizator.entity.Result;
import com.example.hltv_analizator.service.ResultTOP20CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("calculate")
@AllArgsConstructor
public class Controller {

    @Autowired
    private ResultTOP20CalculationService calculationTop20Service;

    @GetMapping("top20")
    public ResponseEntity<List<Result>> getTop20() {
        final List<Result> results = calculationTop20Service.startResultTOP20Calculation();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
