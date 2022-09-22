package com.figo.bing;

import com.upyun.RestManager;
import com.upyun.UpException;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UpYunRestManagerUtils {

    public static RestManager getRestManager(){
		String BUCKET_NAME=System.getProperty("BUCKET_NAME","bing-figo");
		String OPERATOR_NAME=System.getProperty("OPERATOR_NAME","sffchxd");
		String OPERATOR_PWD=System.getProperty("OPERATOR_PWD","xxx");
        return new RestManager(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
    }

    public static Response uploadFile(Image image ) throws UpException, IOException {
        String fileUrl=image.getRealUrl();
        URL url = new URL(fileUrl);
        InputStream bis = url.openStream();
        //支持http特定功能
        //HttpURLConnection    httpUrl = (HttpURLConnection) url.openConnection();
        //httpUrl.connect();
        //缓存输入流,提供了一个缓存数组,每次调用read的时候会先尝试从缓存区读取数据
        //InputStream bis = httpUrl.getInputStream();
        //BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int len;
        byte[] bs = new byte[1024];
        while ((len = bis.read(bs)) != -1) {
            bout.write(bs, 0, len);
        }
        Map<String, String> params = new HashMap<String, String>();
        // 设置待上传文件的 Content-MD5 值
        // 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
        //params.put(RestManager.PARAMS.CONTENT_MD5.getValue(), UpYunUtils.md5(file, 1024));

        // 设置待上传文件的"访问密钥"
        // 注意：
        // 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
        // 举例：
        // 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
        // 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
        //params.put(RestManager.PARAMS.CONTENT_SECRET.getValue(), "bac");
        Response result = getRestManager().writeFile(image.toSavePath()+"/"+fileUrl.substring(fileUrl.lastIndexOf("=")+1), bout.toByteArray(), params);
        bout.flush();
        bis.close();
        return result;
    }
}
