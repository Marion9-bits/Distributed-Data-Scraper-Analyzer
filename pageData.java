package com.scraper.model;

import java.util.Set;

public class PageData {

    private final String url;
    private final String title;
    private final String html;
    private final Set<String> links;

    public PageData(String url, String title, String html, Set<String> links) {
        this.url = url;
        this.title = title;
        this.html = html;
        this.links = links;
    }

    public String getUrl() { return url; }
    public String getTitle() { return title; }
    public String getHtml() { return html; }
    public Set<String> getLinks() { return links; }
}