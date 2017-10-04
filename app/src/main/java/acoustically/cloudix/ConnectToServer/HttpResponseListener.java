package acoustically.cloudix.ConnectToServer;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by acoustically on 17. 10. 3.
 */

public abstract class HttpResponseListener extends Handler{
  protected abstract void httpResponse(JSONObject json);
  protected abstract void httpExcepted();

  @Override
  public void handleMessage(Message msg) {
    if(msg.what==1) {
      httpResponse((JSONObject)msg.obj);
    } else {
      httpExcepted();
    }
    super.handleMessage(msg);
  }
}
