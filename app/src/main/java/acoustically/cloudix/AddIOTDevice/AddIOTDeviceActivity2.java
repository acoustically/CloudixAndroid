package acoustically.cloudix.AddIOTDevice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import acoustically.cloudix.ConnectToServer.HttpConnector;
import acoustically.cloudix.ConnectToServer.HttpResponseListener;
import acoustically.cloudix.ConnectToServer.JSONObjectWithToken;
import acoustically.cloudix.ConnectToServer.Server;
import acoustically.cloudix.Global;
import acoustically.cloudix.MainActivity;
import acoustically.cloudix.R;

public class AddIOTDeviceActivity2 extends AppCompatActivity {
  AddIOTDeviceActivity2 activity = this;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_iot_device2);
  }

  public void onClickAdd(View view) {

    HttpConnector connector = new HttpConnector(Server.getUrl("switchs/new-users-switchs.json"));
    try {
      connector.post(buildJson(), new HttpResponseListener() {
        @Override
        public void httpResponse(JSONObject json) {
          try {
            if (json.getString("response").equals("success")) {
              navigate();
            }
          } catch (Exception e) {

          }
        }

        @Override
        public void httpExcepted() {
          Toast.makeText(activity, "Server Error", Toast.LENGTH_LONG).show();
        }
      });
    } catch (Exception e) {

    }
  }

  private void navigate() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    ((AddIOTDeviceActivity)Global.Obj1).finish();
    ((AddIOTDeviceActivity1)Global.Obj2).finish();
    finish();
  }

  private JSONObject buildJson() throws Exception{
    JSONObject button1Json = new JSONObject();
    JSONObject button2Json = new JSONObject();
    Intent intent = getIntent();
    String serial = intent.getStringExtra("serial");
    EditText editTextSwitch1 = (EditText) findViewById(R.id.editTextSwitch1);
    EditText editTextSwitch2 = (EditText) findViewById(R.id.editTextSwitch2);
    String button1Name = editTextSwitch1.getText().toString();
    String button2Name = editTextSwitch2.getText().toString();
    JSONArray buttons = new JSONArray();
    if(!button1Name.matches("")) {
      button1Json.put("position", 1);
      button1Json.put("name", button1Name);
      buttons.put(button1Json);
    }
    if(!button2Name.matches("")) {
      button2Json.put("position", 2);
      button2Json.put("name", button2Name);
      buttons.put(button2Json);
    }
    JSONObject json = new JSONObjectWithToken();
    json.put("serial", serial);
    json.put("buttons", buttons);
    return json;
  }
}
