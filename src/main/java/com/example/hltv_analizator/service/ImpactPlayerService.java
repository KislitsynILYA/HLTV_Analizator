package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.ImpactPlayer;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Setter
public class ImpactPlayerService {

    @Autowired
    private Repository dao;

    @Autowired
    private ImpactPlayerCalculationService impactPlayerCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_impact}")
    private String parse_impact;

    void startImpactPlayers() {
        if (dao.impactPlayerIsEmpty()) {
            dao.updateImpactPlayer_tier1();
            dao.updateImpactPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        short tier_1_id = 1;
        short tier_2_id = 2;
        for (int i = 0; i < hltv_ids.size(); i++) {
            parsingStatsForImpact(hltv_ids.get(i), tier_1_id, url_tier_1);
            parsingStatsForImpact(hltv_ids.get(i), tier_2_id, url_tier_2);
        }
        impactPlayerCalculationService.startImpactPlayersCalculation();
    }

    void parsingStatsForImpact(Integer hltv_id, short tier, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_maps_numb);
        Element titleElements_2 = document.selectFirst(parse_impact);
        int maps;
        double impact;
        if (titleElements_1 == null){
            maps = 0;
            impact = 0.0;
        }
        else {
            maps = Integer.parseInt(titleElements_1.text());
            impact = Double.parseDouble(titleElements_2.text());
        }
        if (tier == 1) {
            ImpactPlayer impactPlayer_tier1 = dao.getByIdImpactPlayer_tier1(hltv_id);
            impactPlayer_tier1.setMaps_numb(maps);
            impactPlayer_tier1.setImpact(impact);
            dao.saveImpactPlayer(impactPlayer_tier1);
        }
        else {
            ImpactPlayer impactPlayer_tier2 = dao.getByIdImpactPlayer_tier2(hltv_id);
            impactPlayer_tier2.setMaps_numb(maps);
            impactPlayer_tier2.setImpact(impact);
            dao.saveImpactPlayer(impactPlayer_tier2);
        }
        webDriver.quit();
    }
}
