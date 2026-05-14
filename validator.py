def is_valid(page):
    if not page.get("url"):
        return False
    if len(page.get("text", "")) < 50:
        return False
    return True