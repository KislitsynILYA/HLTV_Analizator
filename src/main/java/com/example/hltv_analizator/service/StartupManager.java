package com.example.hltv_analizator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(ApplicationArguments args) {

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
