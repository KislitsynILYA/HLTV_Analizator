package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.KastPlayer;
import jakarta.annotation.PostConstruct;
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

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_kast}")
    private String parse_kast;
    @PostConstruct
    void startKastPlayers() {
        if (dao.kastPlayerIsEmpty()) {
            dao.updateKastPlayer_tier1();
            dao.updateKastPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        for (int i = 0; i < hltv_ids.size(); i++) {
            WebDriver webDriver_1 = new ChromeDriver();
            webDriver_1.get("https://www.hltv.org/stats/players/" + hltv_ids.get(i) + url_tier_1);
            Document document_1 = Jsoup.parse(webDriver_1.getPageSource());
            Element titleElements_1 = document_1.selectFirst(parse_maps_numb);
            Element titleElements_2 = document_1.selectFirst(parse_kast);
            Integer maps_tier_1;
            double kast_tier_1;
            if (titleElements_1 == null){
                maps_tier_1 = 0;
                kast_tier_1 = 0.0;
            }
            else {
                maps_tier_1 = Integer.parseInt(titleElements_1.text());
                kast_tier_1 = Double.parseDouble(titleElements_2.text().substring(0, titleElements_2.text().length() - 1));
            }
            KastPlayer kastPlayer_tier1 = dao.getByIdKastPlayer_tier1(hltv_ids.get(i));
            kastPlayer_tier1.setMaps_numb(maps_tier_1);
            kastPlayer_tier1.setKast(kast_tier_1);
            dao.saveKastPlayer(kastPlayer_tier1);
            webDriver_1.quit();
            WebDriver webDriver_2 = new ChromeDriver();
            webDriver_2.get("https://www.hltv.org/stats/players/"+ hltv_ids.get(i) + url_tier_2);
            Document document_2 = Jsoup.parse(webDriver_2.getPageSource());
            Element titleElements_3 = document_2.selectFirst(parse_maps_numb);
            Element titleElements_4 = document_2.selectFirst(parse_kast);
            Integer maps_tier_2;
            double kast_tier_2;
            if (titleElements_3 == null){
                maps_tier_2 = 0;
                kast_tier_2 = 0.0;
            }
            else {
                maps_tier_2 = Integer.parseInt(titleElements_3.text());
                kast_tier_2 = Double.parseDouble(titleElements_4.text().substring(0, titleElements_4.text().length() - 1));
            }
            KastPlayer kastPlayer_tier2 = dao.getByIdKastPlayer_tier2(hltv_ids.get(i));
            kastPlayer_tier2.setMaps_numb(maps_tier_2);
            kastPlayer_tier2.setKast(kast_tier_2);
            dao.saveKastPlayer(kastPlayer_tier2);
            webDriver_2.quit();
        }
    }
}
