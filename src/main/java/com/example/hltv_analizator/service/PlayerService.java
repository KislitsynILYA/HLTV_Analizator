package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.Player;
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
public class PlayerService {

    @Autowired
    private Repository dao;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.parse_alias}")
    private String parse_alias;

    @PostConstruct
    void startPlayers() {
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        for (int i = 0; i < hltv_ids.size(); i++) {
            WebDriver webDriver = new ChromeDriver();
            webDriver.get("https://www.hltv.org/stats/players/"+ hltv_ids.get(i) + url_tier_1);
            Document document = Jsoup.parse(webDriver.getPageSource());
            Element titleElements = document.selectFirst(parse_alias);
            String alias = titleElements.text();
            Player player = dao.getById(hltv_ids.get(i));
            player.setAlias(alias);
            dao.savePlayer(player);
            webDriver.quit();
        }
    }
}
