package PHPwindTest;

import Interface.Profession;
import Utils.DataUtils;
import Utils.ExcelIterator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-04-06.
 */
public class TestRegister {
    private String token;
    @BeforeTest
    public void before(){
        token = Profession.getToken();
    }
    @DataProvider(name = "reg")
    public Iterator<Object[]> data() throws IOException {
        return new ExcelIterator("testData/phpWind","注册");
    }
    @Test(dataProvider = "reg")
    public void testRegister(Map<String,String>map){
        String response = Profession.register(map,token);
        String actual = DataUtils.htmlParse(response,map.get("解析参数"));
        Assert.assertEquals(actual,map.get("预期结果"));
    }
}
