package Utils;


import java.util.ResourceBundle;

/**
 * Created by ${user} on 2017-04-14.
 */
public class Config {
    private static ResourceBundle rb = ResourceBundle.getBundle("config");
    public final static String HOST_URL = rb.getString("host.url");
    public final static int REQUEST_TYPE = Integer.valueOf(rb.getString("request.type"));
    public final static String FILE_PATH = rb.getString("file.path");
}
