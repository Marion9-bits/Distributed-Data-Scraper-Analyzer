# Distributed-Data-Scraper & Analyzer

A high-performance distributed web scraping and data processing system using Java + Python.

## Features

### Java Scraper
- Multi-threaded crawling engine
- URL frontier with deduplication
- HTML parsing using Jsoup
- Scalable worker pool
- Retry-safe HTTP client

### Python Pipeline
- HTML cleaning & normalization
- Data validation rules
- Dataset generation (CSV)
- Ready for ML training pipelines

## Architecture

Java Scraper → raw JSONL → Python Processor → Clean Dataset (CSV)

## Run Java Crawler

```bash
cd java-scraper
gradle run
