package acoustically.cloudix.AddIOTDevice;

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
import acoustically.cloudix.R;

public class AddIOTDeviceActivity1 extends AppCompatActivity {
  AddIOTDeviceActivity1 activity = this;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_iot_device1);
  }

  public void onClickNext(View view) {
    Global.Obj2 = this;
    HttpConnector connector = new HttpConnector(Server.getUrl("new-device-password.json"));
    try {
      connector.post(buildJson(), new HttpResponseListener() {
        @Override
        public void httpResponse(JSONObject json) {
          try {
            if (json.getString("response").equals("success")) {
              navigate(getSerial());
            } else {
              Toast.makeText(activity, json.getString("response"), Toast.LENGTH_LONG).show();
            }
          } catch (Exception e) {
            Toast.makeText(activity, "Error occur", Toast.LENGTH_LONG).show();
            e.printStackTrace();
          }
        }

        @Override
        public void httpExcepted() {
          Toast.makeText(activity, "Server Error", Toast.LENGTH_LONG).show();
        }
      });
    } catch (Exception e) {
      Log.e("ERROR", "http request error");
      e.printStackTrace();
      Toast.makeText(this, "Error occur", Toast.LENGTH_LONG).show();
    }
  }
  private JSONObject buildJson() throws Exception{
    Intent intent = getIntent();
    final String serial = intent.getStringExtra("serial");
    EditText editTextPassword = (EditText) findViewById(R.id.editTextDeviceNewPassword);
    String password = editTextPassword.getText().toString();
    JSONObject json = new JSONObject();
    json.put("newPassword", password);
    json.put("serial", serial);
    return json;
  }
  private void navigate(String serial) {
    Intent intent = new Intent(this, AddIOTDeviceActivity2.class);
    intent.putExtra("serial", serial);
    startActivity(intent);
  }
  private String getSerial() {
    Intent intent = getIntent();
    return intent.getStringExtra("serial");
  }
}
