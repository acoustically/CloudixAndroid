package acoustically.cloudix;

import android.os.Handler;

import org.json.JSONObject;

/**
 * Created by acoustically on 17. 10. 3.
 */

public interface HttpResponseListener {
  void HttpResponse(JSONObject json);
}
