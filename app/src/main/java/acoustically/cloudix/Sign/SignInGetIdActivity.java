package acoustically.cloudix.Sign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import acoustically.cloudix.ConnectToServer.HttpConnector;
import acoustically.cloudix.ConnectToServer.HttpResponseListener;
import acoustically.cloudix.ConnectToServer.JSONObjectWithToken;
import acoustically.cloudix.ConnectToServer.Server;
import acoustically.cloudix.Global;
import acoustically.cloudix.R;

public class SignInGetIdActivity extends AppCompatActivity {
  SignInGetIdActivity activity = this;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in_get_id);
  }

  public void onClickNext(View view) {
    Global.Obj2 = this;
    EditText editTextId = (EditText)findViewById(R.id.sign_in_id);
    final String id = editTextId.getText().toString();
    try {
      queryToServer(id, "sign-in-id.json", SignInGetPasswordActivity.class);
    } catch (Exception e) {
      Log.e("ERROR", "Http request failed json");
    }
  }
  protected void queryToServer(final String id, final String action, final Class class_) throws Exception {
    JSONObject json = new JSONObjectWithToken();
    json.put("id", id);
    HttpConnector connector = new HttpConnector(Server.getUrl(action));
    connector.post(json, new HttpResponseListener() {
      @Override
      public void httpResponse(JSONObject json) {
        try {
          if (json.getString("response").equals("success")) {
            navigateActivity(id, class_);
          } else {
            Toast.makeText(activity, json.getString("message"), Toast.LENGTH_LONG).show();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void httpExcepted() {
        Toast.makeText(activity, "Server Error", Toast.LENGTH_LONG).show();
      }
    });
  }
  protected void navigateActivity(String id, Class _class) {
    Intent intent = new Intent(this, _class);
    intent.putExtra("data", id);
    startActivity(intent);
  }
}
