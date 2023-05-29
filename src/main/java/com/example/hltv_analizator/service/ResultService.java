package com.example.hltv_analizator.service;

import com.example.hltv_analizator.dao.Repository;
import com.example.hltv_analizator.entity.Result;
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
public class ResultService {

    @Autowired
    private Repository dao;

    @Autowired
    private ResultCalculationService resultCalculationService;

    @Value("${trace.url_tier_1}")
    private String url_tier_1;

    @Value("${trace.parse_alias}")
    private String parse_alias;

    @Value("${trace.parse_team}")
    private String parse_team;

    @Value("${trace.full_name}")
    private String full_name;

    @Value("${trace.parse_age}")
    private String parse_age;

    void startResults() {
        if (dao.resultIsEmpty()) {
            dao.updateResult();
        }
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        List<Integer> hltv_ids = dao.getAllHltvId();
        for (int i = 0; i < 3; i++) {
            parsingInfoForResult(hltv_ids.get(i), url_tier_1);
        }
        resultCalculationService.startResultCalculation();
    }

    void parsingInfoForResult(Integer hltv_id, String url) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.hltv.org/stats/players/" + hltv_id + url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Element titleElements_1 = document.selectFirst(parse_alias);
        Element titleElements_2 = document.selectFirst(parse_team);
        Element titleElements_3 = document.selectFirst(full_name);
        Element titleElements_4 = document.selectFirst(parse_age);

        String alias = titleElements_1.text();
        String team = titleElements_2.text();
        String [] full_name = titleElements_3.text().split(" ");
        String name = full_name[0];
        String last_name = full_name[1];
        int spaceIndex = titleElements_4.text().indexOf(" ");
        Integer age = Integer.parseInt(titleElements_4.text().substring(0, spaceIndex));
        String country = titleElements_3.selectFirst("img").attr("title");

        Result result = dao.getByIdResults(hltv_id);
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
