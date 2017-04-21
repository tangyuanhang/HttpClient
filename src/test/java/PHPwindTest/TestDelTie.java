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
public class TestDelTie {
    private String token;
    @BeforeTest
    public void befor(){
        token = Profession.getToken();
        Profession.login(token);
    }
    @DataProvider(name = "del")
    public Iterator<Object[]> data() throws IOException {
        return new ExcelIterator("testData/phpWind","删帖");
    }
    @Test(dataProvider = "del")
    public void testDelTie(Map<String,String>map){
        String response = Profession.deleteTie(map,token);
        System.out.println(response);
    }
}
