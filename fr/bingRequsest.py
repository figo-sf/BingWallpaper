# -*- coding: utf-8 -*-
import logging
import json
import requests

url = "https://api.github.com/repos/figo-sf/BingWallpaper/dispatches"

payload = json.dumps({
  "event_type": "webhook-1",
  "client_payload": {
    "unit": False,
    "integration": True
  }
})
headers = {
  'Accept': 'application/vnd.github+json',
  'Authorization': 'Bearer ghp_XXXXX',
}

def handler(event, context):
  # evt = json.loads(event)
  logger = logging.getLogger()
  logger.info('hello world')
  response = requests.request("POST", url, headers=headers, data=payload)
  logger.info(response.text)
  return response.text
 # 创建存储库调度事件 更新 MD book 中的背景样式
 # https://docs.github.com/en/rest/repos/repos#create-a-repository-dispatch-event
 # 腾讯云函数 https://console.cloud.tencent.com/scf/ 定时任务每天0点触发执行
 # pip3 install -r requirements.txt -t ./ 在src目录下执行
 # requirements.txt中 requests==2.28.1





