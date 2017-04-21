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
 * Created by Administrator on 2017-04-07.
 */
public class TestControl {
    private String token;
    @BeforeTest
    public void before(){
        token = Profession.getToken();
    }
    @DataProvider(name = "control")
    public Iterator<Object[]> data() throws IOException {
        return new ExcelIterator("testData/phpWind","登录后台管理");
    }
    @Test(dataProvider = "control")
    public void testControl(Map<String,String>map){
        String response = Profession.control(map,token);
        System.out.println(response);
    }

}
