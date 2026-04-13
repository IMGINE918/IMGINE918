import os
from pathlib import Path

from dotenv import load_dotenv

env_path = Path(__file__).resolve().parent.parent / ".env"
load_dotenv(dotenv_path=env_path)


class AIConfig:
    API_KEY = os.getenv("AI_API_KEY", "")
    BASE_URL = os.getenv("AI_BASE_URL", "https://api.deepseek.com")
    MODEL = os.getenv("AI_MODEL", "deepseek-chat")

    @classmethod
    def get_client_config(cls):
        return {
            "api_key": cls.API_KEY,
            "base_url": cls.BASE_URL,
            "model": cls.MODEL,
        }


if __name__ == "__main__":
    print(f"Loaded API base URL: {AIConfig.BASE_URL}")
    print(f"Loaded model: {AIConfig.MODEL}")
    if not AIConfig.API_KEY:
        print("Warning: AI_API_KEY is empty. Configure it in the repository .env file.")
    else:
        print("AI_API_KEY loaded successfully.")
