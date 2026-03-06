import os
from dotenv import load_dotenv
from pathlib import Path

# 1. 自动向上查找 .env 文件 (从 mcp-server 查到根目录)
env_path = Path(__file__).resolve().parent.parent / ".env"
load_dotenv(dotenv_path=env_path)

class AIConfig:
    # 获取环境变量，如果没有则使用默认值
    API_KEY = os.getenv("AI_API_KEY", "YOUR_KEY_HERE")
    BASE_URL = os.getenv("AI_BASE_URL", "https://api.deepseek.com")
    MODEL = os.getenv("AI_MODEL", "deepseek-chat")

    @classmethod
    def get_client_config(cls):
        """返回 OpenAI 兼容格式的配置字典"""
        return {
            "api_key": cls.API_KEY,
            "base_url": cls.BASE_URL
        }

if __name__ == "__main__":
    # 测试是否读取成功
    print(f"✅ 已加载 API 配置: {AIConfig.BASE_URL}")
    print(f"✅ 模型设置为: {AIConfig.MODEL}")
    if AIConfig.API_KEY == "YOUR_KEY_HERE":
        print("❌ 注意: API_KEY 仍为默认值，请在根目录 .env 文件中配置。")
    else:
        print("🎉 API_KEY 已成功从 .env 加载。")
