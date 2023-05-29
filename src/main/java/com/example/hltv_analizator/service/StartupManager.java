package com.example.hltv_analizator.service;

import com.example.hltv_analizator.controller.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class StartupManager implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private AdrPlayerService adrPlayerService;

    @Autowired
    private DprPlayerService dprPlayerService;

    @Autowired
    private ImpactPlayerService impactPlayerService;

    @Autowired
    private KastPlayerService kastPlayerService;

    @Autowired
    private KprPlayerService kprPlayerService;

    @Autowired
    private RatingPlayerService ratingPlayerService;

    @Autowired
    private PlayoffRatingPlayerCalculationService playoffRatingPlayerCalculationService;

    @Autowired
    private PlayerAchievementCalculationService playerAchievementCalculationService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private Controller controller;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        playerService.startPlayers();

        adrPlayerService.startAdrPlayers();

        dprPlayerService.startDprPlayers();

        impactPlayerService.startImpactPlayers();

        kastPlayerService.startKastPlayers();

        kprPlayerService.startKprPlayers();

        ratingPlayerService.startRatingPlayers();

        playoffRatingPlayerCalculationService.startPlayoffRatingPlayersCalculation();

        playerAchievementCalculationService.startPlayerAchievementCalculation();

        resultService.startResults();

        SpringApplication.exit(applicationContext, () -> 0);

    }
}
