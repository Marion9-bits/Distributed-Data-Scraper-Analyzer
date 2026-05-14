package com.scraper.crawler;

import com.scraper.frontier.UrlFrontier;
import com.scraper.http.HttpClient;
import com.scraper.model.PageData;
import com.scraper.parser.HtmlParser;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

    private final UrlFrontier frontier;
    private final HttpClient client;
    private final BlockingQueue<PageData> outputQueue;
    private final int maxPages;

    public Worker(UrlFrontier frontier, HttpClient client,
                  BlockingQueue<PageData> outputQueue, int maxPages) {
        this.frontier = frontier;
        this.client = client;
        this.outputQueue = outputQueue;
        this.maxPages = maxPages;
    }

    @Override
    public void run() {
        while (frontier.size() < maxPages) {
            String url = frontier.next();
            if (url == null) continue;

            try {
                String html = client.get(url);
                PageData data = HtmlParser.parse(url, html);

                outputQueue.put(data);
                frontier.addLinks(data.getLinks());

            } catch (Exception e) {
                System.err.println("Failed: " + url);
            }
        }
    }
}