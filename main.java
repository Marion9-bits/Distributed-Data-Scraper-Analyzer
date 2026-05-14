package com.scraper;

import com.scraper.crawler.Crawler;

public class Main {
    public static void main(String[] args) {
        String seedUrl = "https://example.com";

        Crawler crawler = new Crawler(
                seedUrl,
                8,        // threads
                500       // max pages
        );

        crawler.start();
    }
}