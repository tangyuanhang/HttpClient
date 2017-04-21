package PHPwindTest;


import Interface.Profession;
import Utils.AssertionListener;
import Utils.Assertions;
import Utils.DataUtils;
import Utils.ExcelIterator;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-04-04.
 */
@Listeners({AssertionListener.class})
public class TestLogin {
  Logger logger = Logger.getLogger(TestLogin.class);
    private String token;
  @BeforeTest
    public void getToken(){
      token = Profession.getToken();
  }
  @DataProvider(name = "login")
    public Iterator<Object[]> data() throws IOException {
        return new ExcelIterator("testData/phpWind","login");
  }
  @Test(dataProvider = "login")
    public void login(Map<String,String>map){
      String response = Profession.login(map,token);
      String actual = DataUtils.htmlParse(response,map.get("实际结果"));
      Assertions.verifyEquals(true,actual.contains(map.get("预期结果")));
  }
}