package com.scraper.crawler;

import com.scraper.frontier.UrlFrontier;
import com.scraper.http.HttpClient;
import com.scraper.model.PageData;

import java.util.concurrent.*;

public class Crawler {

    private final ExecutorService pool;
    private final UrlFrontier frontier;
    private final HttpClient httpClient;
    private final int maxPages;
    private final BlockingQueue<PageData> outputQueue = new LinkedBlockingQueue<>();

    public Crawler(String seed, int threads, int maxPages) {
        this.pool = Executors.newFixedThreadPool(threads);
        this.frontier = new UrlFrontier();
        this.httpClient = new HttpClient();
        this.maxPages = maxPages;

        frontier.add(seed);
    }

    public void start() {
        for (int i = 0; i < 8; i++) {
            pool.submit(new Worker(frontier, httpClient, outputQueue, maxPages));
        }

        new Thread(this::consumeOutput).start();
        pool.shutdown();
    }

    private void consumeOutput() {
        while (true) {
            try {
                PageData data = outputQueue.take();
                System.out.println("[SCRAPED] " + data.getUrl());
            } catch (Exception ignored) {}
        }
    }
}