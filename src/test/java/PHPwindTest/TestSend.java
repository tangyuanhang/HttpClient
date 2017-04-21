package PHPwindTest;

import Interface.Profession;
import Utils.Config;
import Utils.DataUtils;
import Utils.ExcelIterator;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Administrator on 2017-04-03.
 */
public class TestSend {
    String token;
 @BeforeTest
    public void afterTest(){
     token = Profession.getToken();
     Profession.login(token);
     System.out.println("token值为：-------"+token);
 }
 @DataProvider(name = "send")
    public Iterator<Object[]> data() throws IOException {
        return new ExcelIterator(Config.FILE_PATH,"发帖");
 }
 @Test(dataProvider = "send")
    public void send(Map<String,String> map){
        String response = Profession.send(map,token);
        String actual = DataUtils.jsonParse(response,"$.state");
        String expected = map.get("预期结果");

    }
}
