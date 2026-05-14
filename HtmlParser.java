package com.scraper.parser;

import com.scraper.model.PageData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.stream.Collectors;

public class HtmlParser {

    public static PageData parse(String url, String html) {
        Document doc = Jsoup.parse(html, url);

        Set<String> links = doc.select("a[href]")
                .stream()
                .map(e -> e.absUrl("href"))
                .filter(l -> l.startsWith("http"))
                .collect(Collectors.toSet());

        String title = doc.title();

        return new PageData(url, title, html, links);
    }
}