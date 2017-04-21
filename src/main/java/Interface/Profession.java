package Interface;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static InterfaceFrameWork.RequestUtils.*;
import static Utils.DataUtils.htmlParse;

/**
 * Created by Administrator on 2017-04-06.
 */
public class Profession {
    //获取csrf_token值
    public static String getToken(){
        String response = get("http://192.168.1.174/phpwind/index.php");
        return htmlParse(response,"[name=csrf_token]","value");
    }

    //登录测试
    public static String login(Map<String, String> map, String token){
        List<NameValuePair>list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("username",map.get("username")));
        list.add(new BasicNameValuePair("password",map.get("password")));
        list.add(new BasicNameValuePair("csrf_token",token));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }

    //使用admin登录获取权限
    public static void login(String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("username","admin"));
        list.add(new BasicNameValuePair("password","123456"));
        list.add(new BasicNameValuePair("csrf_token",token));
        list.add(new BasicNameValuePair("csrf_token",token));
        String response = post("http://192.168.1.174/phpwind/index.php?m=u&c=login&a=dologin",list);
        if (response.contains("statu")){
            String url= htmlParse(response, ".error_return a", "href");
            get(url);
        }else {
            System.out.println("登录失败");
        }
    }

    //发帖
    public static String send(Map<String, String> map, String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("atc_title",map.get("atc_title")));
        list.add(new BasicNameValuePair("atc_content",map.get("atc_content")));
        list.add(new BasicNameValuePair("pid",""));
        list.add(new BasicNameValuePair("tid",""));
        list.add(new BasicNameValuePair("special","default"));
        list.add(new BasicNameValuePair("reply_notice","1"));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }

    //注册
    public static String register(Map<String, String> map, String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("username",map.get("username")));
        list.add(new BasicNameValuePair("password",map.get("password")));
        list.add(new BasicNameValuePair("repassword",map.get("repassword")));
        list.add(new BasicNameValuePair("email",map.get("email")));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }

    //退出登录
    public static String logOut(){
        return get("http://192.168.1.174/phpwind/index.php?m=u&c=login&a=logout");
    }

    //登入后台管理
    public static String control(Map<String,String> map,String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("username",map.get("username")));
        list.add(new BasicNameValuePair("password",map.get("password")));
        list.add(new BasicNameValuePair("submit",""));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }

    public static String control(String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("username","admin"));
        list.add(new BasicNameValuePair("password","123456"));
        list.add(new BasicNameValuePair("submit",""));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post("http://192.168.1.174/phpwind/admin.php?a=login",list);
    }
    //回帖
    public static String reply(Map<String, String> map, String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("atc_title",map.get("atc_title")));
        list.add(new BasicNameValuePair("atc_content",map.get("atc_content")));
        list.add(new BasicNameValuePair("pid",""));
        list.add(new BasicNameValuePair("tid",map.get("tid")));
        list.add(new BasicNameValuePair("special","default"));
        list.add(new BasicNameValuePair("reply_notice","1"));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }

    //删除用户
    public static String delUser(Map<String,String> map,String token){
        List<NameValuePair>list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("uid",map.get("uid")));
        list.add(new BasicNameValuePair("clear[]",map.get("clear[]1")));
        list.add(new BasicNameValuePair("clear[]",map.get("clear[]2")));
        list.add(new BasicNameValuePair("clear[]",map.get("clear[]3")));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }

    //删帖
    public static String deleteTie(Map<String,String>map,String token){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("reason",map.get("参数_reason")));
        list.add(new BasicNameValuePair("deductCredit",map.get("参数_deductCredit")));
        list.add(new BasicNameValuePair("tids[]",map.get("参数_tids[]")));
        list.add(new BasicNameValuePair("csrf_token",token));
        return post(map.get("请求地址"),list);
    }
}
