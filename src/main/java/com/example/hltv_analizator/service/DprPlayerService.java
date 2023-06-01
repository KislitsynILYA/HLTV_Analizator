package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.DprPlayer;
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
public class DprPlayerService {

    @Autowired
    private Repository dao;

    @Autowired
    private DprPlayerCalculationService dprPlayerCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_dpr}")
    private String parse_dpr;

    void startDprPlayers() {
        if (dao.dprPlayerIsEmpty()) {
            dao.updateDprPlayer_tier1();
            dao.updateDprPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        short tier_1_id = 1;
        short tier_2_id = 2;
        for (int i = 0; i < hltv_ids.size(); i++) {
            parsingStatsForDpr(hltv_ids.get(i), tier_1_id, url_tier_1);
            parsingStatsForDpr(hltv_ids.get(i), tier_2_id, url_tier_2);
        }
        dprPlayerCalculationService.startDprPlayersCalculation();
    }

    void parsingStatsForDpr(Integer hltv_id, short tier, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_maps_numb);
        Element titleElements_2 = document.selectFirst(parse_dpr);
        int maps;
        double dpr;
        if (titleElements_1 == null){
            maps = 0;
            dpr = 0.0;
        }
        else {
            maps = Integer.parseInt(titleElements_1.text());
            dpr = Double.parseDouble(titleElements_2.text());
        }
        if (tier == 1) {
            DprPlayer dprPlayer_tier1 = dao.getByIdDprPlayer_tier1(hltv_id);
            dprPlayer_tier1.setMaps_numb(maps);
            dprPlayer_tier1.setDpr(dpr);
            dao.saveDprPlayer(dprPlayer_tier1);
        }
        else {
            DprPlayer dprPlayer_tier2 = dao.getByIdDprPlayer_tier2(hltv_id);
            dprPlayer_tier2.setMaps_numb(maps);
            dprPlayer_tier2.setDpr(dpr);
            dao.saveDprPlayer(dprPlayer_tier2);
        }
        webDriver.quit();
    }
}
