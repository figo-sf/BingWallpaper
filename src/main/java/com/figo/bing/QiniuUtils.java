package com.figo.bing;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class QiniuUtils {

    private static String bucketName = System.getenv("BUCKET_NAME");
    private static String accessKey = System.getenv("OPERATOR_NAME");
    private static String secretKey = System.getenv("OPERATOR_PWD");

    public static void uploadFile(Image image) throws Exception {

        String fileUrl = image.getRealUrl();
        URL url = new URL(fileUrl);
        InputStream bis = url.openStream();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int len;
        byte[] bs = new byte[1024];
        while ((len = bis.read(bs)) != -1) {
            bout.write(bs, 0, len);
        }
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response r = uploadManager.put(bout.toByteArray(), image.toSavePath() + "/" + fileUrl.substring(fileUrl.lastIndexOf("=") + 1), token);
        bout.flush();
        bis.close();
    }
}
