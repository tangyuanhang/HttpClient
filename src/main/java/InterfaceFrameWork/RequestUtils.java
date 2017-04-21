package InterfaceFrameWork;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;


/**
 * 接口测试，包含post、get、delete、put请求方法
 */
public class RequestUtils {
private static CloseableHttpClient httpClient = HttpClients.custom()
        .disableAutomaticRetries()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build();
private static Logger logger = Logger.getLogger(RequestUtils.class);

    public static String getUri(String url, List<NameValuePair>... lists) {
        if (lists.length != 0) {
            url = url + "?";
            Iterator<NameValuePair> iterator = lists[0].iterator();
            while (iterator.hasNext()) {
                url = url + iterator.next().toString() + "&";
            }
        }
        return url;
    }

    private static String response(HttpUriRequest uriRequest) {
        String strResponse;
        try {
            logger.info("开始发送请求");
            CloseableHttpResponse response = httpClient.execute(uriRequest);
            if (response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity.getContentLength() != 0) {
                        strResponse = EntityUtils.toString(entity, "utf-8");
                        return strResponse;
                    } else {
                       logger.warn("响应实体为空");
                        return null;
                    }
                } else {
                    logger.error("响应错误，响应状态码为：" + statusCode);
                }
            } else {
                logger.error("请求失败！");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String url,List<NameValuePair>... lists) {
        String uri = getUri(url, lists);
        HttpGet get = new HttpGet(uri);
        return response(get);
    }

    public static String delete(String url,List<NameValuePair>... lists) {
        String uri = getUri(url, lists);
        HttpDelete delete = new HttpDelete(uri);
        return response(delete);
    }

    public static String post(String url,List<NameValuePair>... lists) {
        HttpPost post = new HttpPost(url);
        if (lists.length != 0) {
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(lists[0]);
                post.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return response(post);
    }

    public static String post(String url,JSONObject jsonObject) {
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        entity.setContentType("*/*");
        entity.setContentEncoding("utf-8");
        post.setEntity(entity);
        return response(post);
    }

    public static String post(String url,String filePath) {
        File file = new File(filePath);
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpEntity entity = builder.addBinaryBody("file", file).build();
        post.setEntity(entity);
        return response(post);
    }

    public static String put(String url,String filePath) {
        File file = new File(filePath);
        HttpPut put = new HttpPut(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpEntity entity = builder.addBinaryBody("file", file).build();
        put.setEntity(entity);
        return response(put);
    }

    public static String put(String url,List<NameValuePair>... lists) {
        HttpPut put = new HttpPut(url);
        if (lists.length != 0) {
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(lists[0]);
                put.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return response(put);
    }

    public static String put(String url,JSONObject jsonObject) {
        HttpPut put = new HttpPut(url);
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        entity.setContentType(".*/*.");
        put.setEntity(entity);
        return response(put);
    }

}
