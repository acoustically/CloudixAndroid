package acoustically.cloudix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class SignInGetPassword extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in_get_password);
  }
  public void onClickSign(View view) {
    EditText editTextPassword = (EditText)findViewById(R.id.sign_in_password);
    String password = editTextPassword.getText().toString();
    Intent intent = getIntent();
    String id = intent.getStringExtra("data");
    try {
      qeuryToServer(id, password, "sign-in.json");
    } catch (Exception e) {
      Log.e("ERROR", "http request failed - json");
    }
  }
  protected void navigateToMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }
  protected void qeuryToServer(String id, String password, String action) throws Exception {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("password", password);
    HttpConnector connector = new HttpConnector(Server.getUrl(action));
    connector.post(json, new HttpResponseListener() {
      @Override
      public void HttpResponse(JSONObject json) {
        try {
          if (json.getString("response").equals("success")) {
            navigateToMainActivity();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
