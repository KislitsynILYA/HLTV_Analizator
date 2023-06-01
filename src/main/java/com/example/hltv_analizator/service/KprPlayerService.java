package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.KprPlayer;
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
public class KprPlayerService {

    @Autowired
    private Repository dao;

    @Autowired
    private KprPlayerCalculationService kprPlayerCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_kpr}")
    private String parse_kpr;

    void startKprPlayers() {
        if (dao.kprPlayerIsEmpty()) {
            dao.updateKprPlayer_tier1();
            dao.updateKprPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        short tier_1_id = 1;
        short tier_2_id = 2;
        for (int i = 0; i < hltv_ids.size(); i++) {
            parsingStatsForKpr(hltv_ids.get(i), tier_1_id, url_tier_1);
            parsingStatsForKpr(hltv_ids.get(i), tier_2_id, url_tier_2);
        }
        kprPlayerCalculationService.startKprPlayersCalculation();
    }

    void parsingStatsForKpr(Integer hltv_id, short tier, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_maps_numb);
        Element titleElements_2 = document.selectFirst(parse_kpr);
        int maps;
        double kpr;
        if (titleElements_1 == null){
            maps = 0;
            kpr = 0.0;
        }
        else {
            maps = Integer.parseInt(titleElements_1.text());
            kpr = Double.parseDouble(titleElements_2.text());
        }
        if (tier == 1) {
            KprPlayer kprPlayer_tier1 = dao.getByIdKprPlayer_tier1(hltv_id);
            kprPlayer_tier1.setMaps_numb(maps);
            kprPlayer_tier1.setKpr(kpr);
            dao.saveKprPlayer(kprPlayer_tier1);
        }
        else {
            KprPlayer kprPlayer_tier2 = dao.getByIdKprPlayer_tier2(hltv_id);
            kprPlayer_tier2.setMaps_numb(maps);
            kprPlayer_tier2.setKpr(kpr);
            dao.saveKprPlayer(kprPlayer_tier2);
        }
        webDriver.quit();
    }
}
