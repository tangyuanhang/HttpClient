package PHPwindTest;

import Interface.Profession;
import Utils.ExcelIterator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-04-06.
 */
public class TestDelUser {
    private String token;
    @BeforeTest
    public void before(){
        token = Profession.getToken();
        Profession.control(token);
    }
    @DataProvider(name = "del")
    public Iterator<Object[]> data() throws IOException {
        return new ExcelIterator("testData/phpWind","删除用户");
    }
    @Test(dataProvider = "del")
    public void testDelUser(Map<String,String>map){
        String response = Profession.delUser(map,token);
        System.out.println(response);
    }
}
