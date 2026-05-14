import json
import pandas as pd
from cleaner import clean_html
from validator import is_valid

INPUT_FILE = "../java-scraper/output/raw_pages.jsonl"
OUTPUT_FILE = "dataset.csv"

def run_pipeline():
    cleaned = []

    with open(INPUT_FILE, "r") as f:
        for line in f:
            page = json.loads(line)

            text = clean_html(page["html"])
            page["text"] = text

            if is_valid(page):
                cleaned.append({
                    "url": page["url"],
                    "title": page["title"],
                    "text": text
                })

    df = pd.DataFrame(cleaned)
    df.to_csv(OUTPUT_FILE, index=False)

    print(f"Saved {len(df)} clean records")

if _name_ == "_main_":
    run_pipeline()