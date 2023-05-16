//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.RatingPlayer;
//import jakarta.annotation.PostConstruct;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@NoArgsConstructor
//@Setter
//public class RatingPlayerService {
//
//    @Autowired
//    private Repository dao;
//
//    @Value("${trace.url_tier_1}")
//    private String url_tier_1;
//
//    @Value("${trace.url_tier_2}")
//    private String url_tier_2;
//
//    @Value("${trace.parse_maps_numb}")
//    private String parse_maps_numb;
//
//    @Value("${trace.parse_rating}")
//    private String parse_rating;
//    @PostConstruct
//    void startRatingPlayers() {
//        if (dao.ratingPlayerIsEmpty()) {
//            dao.updateRatingPlayer_tier1();
//            dao.updateRatingPlayer_tier2();
//        }
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        List<Integer> hltv_ids = dao.getAllHltvId();
//        for (int i = 0; i < hltv_ids.size(); i++) {
//            WebDriver webDriver_1 = new ChromeDriver();
//            webDriver_1.get("https://www.hltv.org/stats/players/" + hltv_ids.get(i) + url_tier_1);
//            Document document_1 = Jsoup.parse(webDriver_1.getPageSource());
//            Element titleElements_1 = document_1.selectFirst(parse_maps_numb);
//            Element titleElements_2 = document_1.selectFirst(parse_rating);
//            Integer maps_tier_1;
//            double rating_tier_1;
//            if (titleElements_1 == null){
//                maps_tier_1 = 0;
//                rating_tier_1 = 0.0;
//            }
//            else {
//                maps_tier_1 = Integer.parseInt(titleElements_1.text());
//                rating_tier_1 = Double.parseDouble(titleElements_2.text());
//            }
//            RatingPlayer ratingPlayer_tier1 = dao.getByIdRatingPlayer_tier1(hltv_ids.get(i));
//            ratingPlayer_tier1.setMaps_numb(maps_tier_1);
//            ratingPlayer_tier1.setRating(rating_tier_1);
//            dao.saveRatingPlayer(ratingPlayer_tier1);
//            webDriver_1.quit();
//            WebDriver webDriver_2 = new ChromeDriver();
//            webDriver_2.get("https://www.hltv.org/stats/players/"+ hltv_ids.get(i) + url_tier_2);
//            Document document_2 = Jsoup.parse(webDriver_2.getPageSource());
//            Element titleElements_3 = document_2.selectFirst(parse_maps_numb);
//            Element titleElements_4 = document_2.selectFirst(parse_rating);
//            Integer maps_tier_2;
//            double rating_tier_2;
//            if (titleElements_3 == null){
//                maps_tier_2 = 0;
//                rating_tier_2 = 0.0;
//            }
//            else {
//                maps_tier_2 = Integer.parseInt(titleElements_3.text());
//                rating_tier_2 = Double.parseDouble(titleElements_4.text());
//            }
//            RatingPlayer ratingPlayer_tier2 = dao.getByIdRatingPlayer_tier2(hltv_ids.get(i));
//            ratingPlayer_tier2.setMaps_numb(maps_tier_2);
//            ratingPlayer_tier2.setRating(rating_tier_2);
//            dao.saveRatingPlayer(ratingPlayer_tier2);
//            webDriver_2.quit();
//        }
//    }
//}
