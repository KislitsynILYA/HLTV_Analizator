//package com.example.hltv_analizator.service;
//
//import com.example.hltv_analizator.dao.Repository;
//import com.example.hltv_analizator.entity.Player;
//import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@Setter
//public class PlayerService {
//
//    @Autowired
//    private Repository dao;
//    @PostConstruct
//    void startPlayers() throws InterruptedException {
//        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.2214.89 Safari/537.36";
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("user-agent=" + userAgent);
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriver webDriver = new ChromeDriver(options);
//        List<Integer> ids = dao.getAllId();
//        for (int i = 0; i < ids.size(); i++) {
//            webDriver.get("https://www.hltv.org/stats/players/"+ ids.get(i) +"/page?startDate=2021-01-01" +
//                    "&endDate=2021-12-31&event=5604&event=5219&event=5728&event=5553&event=5552&event" +
//                    "=5206&event=5469&event=5554&event=4866&event=5607&event=5730&event=5608&event=6207");
//            Document document = Jsoup.parse(webDriver.getPageSource());
//            Element titleElements = document.selectFirst("body > div.bgPadding > div.widthControl > " +
//                    "div.colCon > div.contentCol > div.stats-section.stats-player.stats-player-overview > " +
//                    "div.playerSummaryStatBox > div.summaryBreakdownContainer > div.summaryShortInfo > " +
//                    "h1.summaryNickname.text-ellipsis");
//            String alias = titleElements.text();
//            Player player = dao.getById(ids.get(i));
//            player.setAlias(alias);
//            dao.savePlayer(player);
//            System.out.println(player);
//            System.out.println(alias);
//        }
//        webDriver.quit();
//    }
//}
