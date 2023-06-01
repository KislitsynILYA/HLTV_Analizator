package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.KastPlayer;
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
public class KastPlayerService {

    @Autowired
    private Repository dao;

    @Autowired
    private KastPlayerCalculationService kastPlayerCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_kast}")
    private String parse_kast;

    void startKastPlayers() {
        if (dao.kastPlayerIsEmpty()) {
            dao.updateKastPlayer_tier1();
            dao.updateKastPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        short tier_1_id = 1;
        short tier_2_id = 2;
        for (int i = 0; i < hltv_ids.size(); i++) {
            parsingStatsForKast(hltv_ids.get(i), tier_1_id, url_tier_1);
            parsingStatsForKast(hltv_ids.get(i), tier_2_id, url_tier_2);
        }
        kastPlayerCalculationService.startKastPlayersCalculation();
    }

    void parsingStatsForKast(Integer hltv_id, short tier, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_maps_numb);
        Element titleElements_2 = document.selectFirst(parse_kast);
        int maps;
        double kast;
        if (titleElements_1 == null){
            maps = 0;
            kast = 0.0;
        }
        else {
            maps = Integer.parseInt(titleElements_1.text());
            kast = Double.parseDouble(titleElements_2.text().substring(0, titleElements_2.text().length() - 1));
        }
        if (tier == 1) {
            KastPlayer kastPlayer_tier1 = dao.getByIdKastPlayer_tier1(hltv_id);
            kastPlayer_tier1.setMaps_numb(maps);
            kastPlayer_tier1.setKast(kast);
            dao.saveKastPlayer(kastPlayer_tier1);
        }
        else {
            KastPlayer kastPlayer_tier2 = dao.getByIdKastPlayer_tier2(hltv_id);
            kastPlayer_tier2.setMaps_numb(maps);
            kastPlayer_tier2.setKast(kast);
            dao.saveKastPlayer(kastPlayer_tier2);
        }
        webDriver.quit();
    }
}
