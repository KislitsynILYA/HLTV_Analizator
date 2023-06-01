package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.RatingPlayer;
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
public class RatingPlayerService {

    @Autowired
    private Repository dao;

    @Autowired
    private RatingPlayerCalculationService ratingPlayerCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.url_tier_2}")
    private String url_tier_2;

    @Value("${trace.parse_maps_numb}")
    private String parse_maps_numb;

    @Value("${trace.parse_rating}")
    private String parse_rating;

    void startRatingPlayers() {
        if (dao.ratingPlayerIsEmpty()) {
            dao.updateRatingPlayer_tier1();
            dao.updateRatingPlayer_tier2();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        short tier_1_id = 1;
        short tier_2_id = 2;
        for (int i = 0; i < hltv_ids.size(); i++) {
            parsingStatsForRating(hltv_ids.get(i), tier_1_id, url_tier_1);
            parsingStatsForRating(hltv_ids.get(i), tier_2_id, url_tier_2);
        }
        ratingPlayerCalculationService.startRatingPlayersCalculation();
    }

    void parsingStatsForRating(Integer hltv_id, short tier, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_maps_numb);
        Element titleElements_2 = document.selectFirst(parse_rating);
        int maps;
        double rating;
        if (titleElements_1 == null){
            maps = 0;
            rating = 0.0;
        }
        else {
            maps = Integer.parseInt(titleElements_1.text());
            rating = Double.parseDouble(titleElements_2.text());
        }
        if (tier == 1) {
            RatingPlayer ratingPlayer_tier1 = dao.getByIdRatingPlayer_tier1(hltv_id);
            ratingPlayer_tier1.setMaps_numb(maps);
            ratingPlayer_tier1.setRating(rating);
            dao.saveRatingPlayer(ratingPlayer_tier1);
        }
        else {
            RatingPlayer ratingPlayer_tier2 = dao.getByIdRatingPlayer_tier2(hltv_id);
            ratingPlayer_tier2.setMaps_numb(maps);
            ratingPlayer_tier2.setRating(rating);
            dao.saveRatingPlayer(ratingPlayer_tier2);
        }
        webDriver.quit();
    }
}
