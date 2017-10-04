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
import acoustically.cloudix.ConnectToServer.Server;
import acoustically.cloudix.Global;
import acoustically.cloudix.IndexActivity;
import acoustically.cloudix.MainActivity;
import acoustically.cloudix.R;

public class SignInGetPasswordActivity extends AppCompatActivity {
  SignInGetPasswordActivity activity = this;
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
    ((IndexActivity)Global.Obj1).finish();
    ((AppCompatActivity)Global.Obj2).finish();
  }
  protected void qeuryToServer(final String id, String password, String action) throws Exception {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("password", password);
    HttpConnector connector = new HttpConnector(Server.getUrl(action));
    connector.post(json, new HttpResponseListener() {
      @Override
      public void httpResponse(JSONObject json) {
        try {
          if (json.getString("response").equals("success")) {
            Global.id = id;
            navigateToMainActivity();
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
}
