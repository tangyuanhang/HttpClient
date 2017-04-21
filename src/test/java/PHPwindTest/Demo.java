package PHPwindTest;

import InterfaceFrameWork.RequestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;


/**
 * Created by Administrator on 2017-04-10.
 */
public class Demo {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    static URI uri;
    static String token = null;
    public static void main(String[] args) throws Exception {

        /**
         * 获取access_token接口  get请求方式
         * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
         */
        //构建URI
            uri = new URIBuilder().setScheme("https")
                    .setHost("api.weixin.qq.com")
                    .setPath("/cgi-bin/token")
                    .setParameter("grant_type","client_credential")
                    .setParameter("appid","wx6635531ea860c5bc")
                    .setParameter("secret","543f85051f4901caf4b79aca6a1dac79")
                    .build();
        //申明请求方式
        HttpGet get = new HttpGet(uri);
        //发送请求、接受响应
        CloseableHttpResponse tokenResponse = httpClient.execute(get);
        //获取响应实体
        String strResponse = EntityUtils.toString(tokenResponse.getEntity(),"utf-8");
        //将实体转换成JSON数据
        JSONObject jsonObject = JSON.parseObject(strResponse);
        //获取access_token值
        token = JSONPath.eval(jsonObject,"$.access_token").toString();

        /**
         * 获取Openid  get请求方式
         * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
         */
        //声明请求方式
        HttpGet openidGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid=");
        //发送请求、获取响应
        CloseableHttpResponse openidResponse = httpClient.execute(openidGet);
        //获取响应实体并转换成字符串
        String strOpenid= EntityUtils.toString(openidResponse.getEntity(),"utf-8");
        System.out.println(strOpenid);
        JSONObject jsonOpenid = JSON.parseObject(strOpenid);
        String openid = JSONPath.eval(jsonOpenid,"$.next_openid").toString();
        System.out.println(openid);

        /**
         * 发送客服消息接口  post请求方式
         * https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
         */
        //将要发送的数据转换成JSON数据
        JSONObject jsonSend = JSON.parseObject("{\n" +
                 "   \"touser\":\""+openid+"\",\n" +
                "    \"msgtype\":\"text\",\n" +
                "    \"text\":\n" +
                "    {\n" +
                "         \"content\":\"Hello World\"\n" +
                "    }\n" +
                "}");
        String strSend = RequestUtils.post("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+ token,jsonSend);
        System.out.println(strSend);
        httpClient.close();
    }
}
