package acoustically.cloudix.ConnectToServer;

/**
 * Created by acoustically on 17. 10. 2.
 */

public class Server {
  public final static String ADDRESS = "http://13.124.7.228";
  public final static int PORT = 3000;

  public static String getUrl(String action) {
    String url = ADDRESS + ":" + PORT + "/" + action;
    return url;
  }
}
