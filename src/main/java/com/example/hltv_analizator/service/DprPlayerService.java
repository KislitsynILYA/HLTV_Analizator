//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.DprPlayer;
//import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@Setter
//public class DprPlayerService {
//    @Autowired
//    private Repository dao;
//    @PostConstruct
//    void startDprPlayers() {
//        if (dao.dprPlayerIsEmpty()) {
//            dao.updateDprPlayer_tier1();
//            dao.updateDprPlayer_tier2();
//        }
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        List<Integer> hltv_ids = dao.getAllHltvId();
//        for (int i = 0; i < hltv_ids.size(); i++) {
//            WebDriver webDriver_1 = new ChromeDriver();
//            webDriver_1.get("https://www.hltv.org/stats/players/" + hltv_ids.get(i) + "/players?startDate=2021-01-01&endDate=" +
//                    "2021-12-31&event=5604&event=5219&event=5728&event=5553&event=5552&event=5206&event=" +
//                    "5469&event=5554&event=4866&event=5607&event=5730&event=5608&event=6207");
//            Document document_1 = Jsoup.parse(webDriver_1.getPageSource());
//            Element titleElements_1 = document_1.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
//                    "div.contentCol > div.stats-section.stats-player.stats-player-overview > div.statistics > " +
//                    "div.columns > div.col.stats-rows.standard-box > " +
//                    "div.stats-row:nth-child(7) > span:nth-child(2)");
//            Element titleElements_2 = document_1.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
//                    "div.contentCol > div.stats-section.stats-player.stats-player-overview > div.statistics > " +
//                    "div.columns > div.col.stats-rows.standard-box:nth-child(2) > " +
//                    "div.stats-row:nth-child(4) > span:nth-child(2)");
//            Integer maps_tier_1;
//            double dpr_tier_1;
//            if (titleElements_1 == null){
//                maps_tier_1 = 0;
//                dpr_tier_1 = 0.0;
//            }
//            else {
//                maps_tier_1 = Integer.parseInt(titleElements_1.text());
//                dpr_tier_1 = Double.parseDouble(titleElements_2.text());
//            }
//            DprPlayer dprPlayer_tier1 = dao.getByIdDprPlayer_tier1(hltv_ids.get(i));
//            dprPlayer_tier1.setMaps_numb(maps_tier_1);
//            dprPlayer_tier1.setDpr(dpr_tier_1);
//            dao.saveDprPlayer(dprPlayer_tier1);
//            webDriver_1.quit();
//            WebDriver webDriver_2 = new ChromeDriver();
//            webDriver_2.get("https://www.hltv.org/stats/players/"+ hltv_ids.get(i) + "/players?startDate=2021-01-01&endDate=" +
//                    "2021-12-31&event=5937&event=5901&event=5853&event=5864&event=5603&event=5454&event=5715&event" +
//                    "=5671&event=5898&event=5602&event=5605&event=5729&event=5606&event=6018&event=6070&event=6069");
//            Document document_2 = Jsoup.parse(webDriver_2.getPageSource());
//            Element titleElements_3 = document_2.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
//                    "div.contentCol > div.stats-section.stats-player.stats-player-overview > div.statistics > " +
//                    "div.columns > div.col.stats-rows.standard-box > " +
//                    "div.stats-row:nth-child(7) > span:nth-child(2)");
//            Element titleElements_4 = document_2.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
//                    "div.contentCol > div.stats-section.stats-player.stats-player-overview > div.statistics > " +
//                    "div.columns > div.col.stats-rows.standard-box:nth-child(2) > " +
//                    "div.stats-row:nth-child(4) > span:nth-child(2)");
//            Integer maps_tier_2;
//            double dpr_tier_2;
//            if (titleElements_3 == null){
//                maps_tier_2 = 0;
//                dpr_tier_2 = 0.0;
//            }
//            else {
//                maps_tier_2 = Integer.parseInt(titleElements_3.text());
//                dpr_tier_2 = Double.parseDouble(titleElements_4.text());
//            }
//            DprPlayer dprPlayer_tier2 = dao.getByIdDprPlayer_tier2(hltv_ids.get(i));
//            dprPlayer_tier2.setMaps_numb(maps_tier_2);
//            dprPlayer_tier2.setDpr(dpr_tier_2);
//            dao.saveDprPlayer(dprPlayer_tier2);
//            webDriver_2.quit();
//        }
//    }
//}
