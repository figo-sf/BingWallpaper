package com.figo.bing;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.LocalDate;

public class Wallpaper {
    private static String BING_URL = "https://cn.bing.com/?scope=web&FORM=BEHPTB";

    public static void main(String[] args) throws Exception {


        Document document = Jsoup.connect(BING_URL).get();
        String url = document.head().select("#preloadBg").attr("href");
        try{
            url = url.substring(0, url.indexOf("&"));
        }catch (Exception e) {
            System.out.println(url);
        }

        LocalDate localDate = LocalDate.now();
        url = url.substring(0, url.lastIndexOf("_")) + "_UHD.jpg";

        String desc = document.select(".musCardCont h3 a").html();
        Image image = new Image(desc, localDate.toString(), url);
        QiniuUtils.uploadFile(image);
        /*Response response = UpYunRestManagerUtils.uploadFile(image);

        if (!response.isSuccessful()) {
            throw new RuntimeException(response.toString());
        }*/
        BingFileUtils.writeBingCss(image);
        BingFileUtils.writeBing(image);
    }
}
