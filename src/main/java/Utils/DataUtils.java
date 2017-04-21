package Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;


/**
 *数据解析
 */
public class DataUtils {
    private static JSONObject strToJson(String jsonResponse){
        if (jsonResponse.equals("")){
            System.out.println("输入的参数不能为空");
            return null;
        }else {
            return JSON.parseObject(jsonResponse);
        }
    }

    /**
     * 从json中解析数据
     */
    public static String jsonParse(String jsonResponse, String jsonPath){
        JSONObject jsonObject = strToJson(jsonResponse);
        if (jsonPath.equals("")){
            System.out.println("输入jsonPath为空,返回原来的Json数据");
            return jsonObject.toString();
        }else if (JSONPath.eval(jsonObject,jsonPath) == null){
            System.out.println("数据没有找到，返回原来的Json数据");
            return jsonObject.toString();
        }else {
            return JSONPath.eval(jsonObject,jsonPath).toString();
        }
    }

    private static Document stringToDoc(String html){
        if (html != null){
        return Jsoup.parse(html);
        }else {
            return null;
        }
    }

    private static Document urlToDoc(String url){
        Document document = null;
        if (url==null || url.equals("")){
            return null;
        }else {
            try {
                document = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return document;
    }

    /**
     *从html中解析数据
     */
    public static String htmlUrlParse(String url,String path){
        Document document = urlToDoc(url);
        if (path == null || path.equals("")){
            System.out.println("输入的参数为空");
            return null;
        }else {
            Element element = document.select(path).first();
            if (element == null){
                System.out.println("元素未找到");
                return null;
            }else {
                return element.text();
            }
        }
    }

    public static String htmlUrlParse(String url, String path, String attr){
        Document document = urlToDoc(url);
        Element element = document.select(path).first();
        if (element == null){
            System.out.println("元素未找到");
            return null;
        }else {
            return element.attr(attr);
        }
    }

    public static String htmlParse(String htmlResponse, String path, String...attr) {
            Document document = stringToDoc(htmlResponse);
            Element element = document.select(path).first();
            if (element !=null){
                if (attr.length == 0){
                    return element.text();
                }else {
                    return element.attr(attr[0]);
                }
            }else {
                System.out.println("未找到元素");
                return null;
            }
    }
}
