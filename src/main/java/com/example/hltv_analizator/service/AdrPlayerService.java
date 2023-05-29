package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.AdrPlayer;
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
public class AdrPlayerService {

    @Autowired
    private Repository dao;

    @Autowired
    private AdrPlayerCalculationService adrPlayerCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_adr}")
    private String parse_adr;

    void startAdrPlayers() throws InterruptedException {
        if (dao.adrPlayerIsEmpty()) {
            dao.updateAdrPlayer_tier1();
            dao.updateAdrPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        short tier_1_id = 1;
        short tier_2_id = 2;
        for (int i = 0; i < 3; i++) {
            parsingStatsForAdr(hltv_ids.get(i), tier_1_id, url_tier_1);
            parsingStatsForAdr(hltv_ids.get(i), tier_2_id, url_tier_2);
        }
        adrPlayerCalculationService.startAdrPlayersCalculation();
    }

    void parsingStatsForAdr(Integer hltv_id, short tier, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_maps_numb);
        Element titleElements_2 = document.selectFirst(parse_adr);
        int maps;
        double adr;
        if (titleElements_1 == null){
            maps = 0;
            adr = 0.0;
        }
        else {
            maps = Integer.parseInt(titleElements_1.text());
            adr = Double.parseDouble(titleElements_2.text());
        }
        if (tier == 1) {
            AdrPlayer adrPlayer_tier1 = dao.getByIdAdrPlayer_tier1(hltv_id);
            adrPlayer_tier1.setMaps_numb(maps);
            adrPlayer_tier1.setAdr(adr);
            dao.saveAdrPlayer(adrPlayer_tier1);
        }
        else {
            AdrPlayer adrPlayer_tier2 = dao.getByIdAdrPlayer_tier2(hltv_id);
            adrPlayer_tier2.setMaps_numb(maps);
            adrPlayer_tier2.setAdr(adr);
            dao.saveAdrPlayer(adrPlayer_tier2);
        }
        webDriver.quit();
    }
}
