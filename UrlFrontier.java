package com.scraper.frontier;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class UrlFrontier {

    private final Queue<String> queue = new ConcurrentLinkedQueue<>();
    private final Set<String> visited = ConcurrentHashMap.newKeySet();

    public void add(String url) {
        if (visited.add(url)) {
            queue.add(url);
        }
    }

    public String next() {
        return queue.poll();
    }

    public void addLinks(Set<String> links) {
        for (String link : links) {
            add(link);
        }
    }

    public int size() {
        return visited.size();
    }
}