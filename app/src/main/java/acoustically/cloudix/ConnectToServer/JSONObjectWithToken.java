package acoustically.cloudix.ConnectToServer;

import org.json.JSONObject;

import acoustically.cloudix.Global;

/**
 * Created by acoustically on 17. 10. 5.
 */

public class JSONObjectWithToken extends JSONObject {
  public JSONObjectWithToken() throws Exception{
    super();
    this.put("token", "acoustically");
    if(Global.id != null) {
      this.put("user_id", Global.id);
    }
  }
}
