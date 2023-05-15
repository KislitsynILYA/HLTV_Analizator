package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.RatingPlayer;
import com.example.hltv_analizator.entity.Result;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Setter
public class ResultService {

    @Autowired
    private Repository dao;
    @PostConstruct
    void startResults() {
        if (dao.resultIsEmpty()) {
            dao.updateResult();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        for (int i = 0; i < 10; i++) {
            WebDriver webDriver = new ChromeDriver();
            webDriver.get("https://www.hltv.org/player/" + hltv_ids.get(i) + "/player");
            Document document_1 = Jsoup.parse(webDriver.getPageSource());
            Element titleElements_1 = document_1.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
                    "div.contentCol > div.playerProfile > div.playerContainer > " +
                    "div.playerInfoWrapper > div.playerNameWrapper > " +
                    "div.playerName > h1.playerNickname");
            Element titleElements_2 = document_1.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
                    "div.contentCol > div.playerProfile > div.playerContainer > " +
                    "div.playerInfoWrapper > div.playerInfo > " +
                    "div.playerInfoRow.playerTeam > span.listRight.text-ellipsis > span");
            Element titleElements_3 = document_1.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
                    "div.contentCol > div.playerProfile > div.playerContainer > " +
                    "div.playerInfoWrapper > div.playerNameWrapper > " +
                    "div.playerName > div.playerRealname");
            Element titleElements_4 = document_1.selectFirst("body > div.bgPadding > div.widthControl > div.colCon > " +
                    "div.contentCol > div.playerProfile > div.playerContainer > " +
                    "div.playerInfoWrapper > div.playerInfo > " +
                    "div.playerInfoRow.playerAge > span.listRight");
            String alias = titleElements_1.text();
            String team = titleElements_2.text();
            String [] full_name = titleElements_3.text().split(" ");
            String name = full_name[0];
            String last_name = full_name[1];
            int spaceIndex = titleElements_4.text().indexOf(" ");
            Integer age = Integer.parseInt(titleElements_4.text().substring(0, spaceIndex));
            String country = titleElements_3.selectFirst("img").attr("title");
            Result result = dao.getByIdResults(hltv_ids.get(i));
            result.setAlias(alias);
            result.setTeam(team);
            result.setName(name);
            result.setLast_name(last_name);
            result.setAge(age);
            result.setCountry(country);
            dao.saveResult(result);
            webDriver.quit();
        }
    }
}
