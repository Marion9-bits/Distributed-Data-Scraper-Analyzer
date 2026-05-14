from dataclasses import dataclass

@dataclass
class CleanPage:
    url: str
    title: str
    text: str