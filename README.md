# BingWallpaper
Bing Wallpaper 
## 20220922 增加又拍云
java 中 要用System.getenv()
``` aidl
System.getProperties 获取jvm 变量
System.getenv() 获取系统环境变量
```
actions 中 这样配置
``` aidl
      - name: Run Java Application
        env:
          OPERATOR_PWD : ${{ secrets.OPERATOR_PWD }}
```