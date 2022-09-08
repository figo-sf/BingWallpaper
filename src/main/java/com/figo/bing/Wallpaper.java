package com.figo.bing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;

public class Wallpaper {
    private static String BING_URL = "https://cn.bing.com/?scope=web&FORM=BEHPTB";
    public static void main(String[] args) throws Exception {


        Document document = Jsoup.connect(BING_URL).get();
        String url =document.head().getElementById("preloadBg").attr("href");
        url = url.substring(0, url.indexOf("&"));

        LocalDate localDate =LocalDate.now();
        url=url.substring(0,url.lastIndexOf("_"))+"_UHD.jpg";
       // System.out.println(url);

        String desc=document.getElementsByClass("musCardCont").get(0).selectXpath("h2/a").get(0).html();
        Image image =new Image(desc,localDate.toString(),url);
        System.out.println(image);
        BingFileUtils.writeBing(image);
    }
}
