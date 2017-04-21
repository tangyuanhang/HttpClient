import Utils.Config;
import org.apache.log4j.Logger;

/**
 * Created by ${user} on 2017-04-13.
 */
public class Demo {
    private static Logger logger = Logger.getLogger(Demo.class);
    public static void main(String[] args) {
        //  记录info 级别的信息
            logger.info("This is info message.");
        //  记录error 级别的信息
            logger.error("This is error message.");
            System.out.println(Config.HOST_URL);
            System.out.println(Config.REQUEST_TYPE);
        }
}
