package acoustically.cloudix.AddIOTDevice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.HttpURLConnection;

import acoustically.cloudix.ConnectToServer.HttpConnector;
import acoustically.cloudix.ConnectToServer.HttpResponseListener;
import acoustically.cloudix.ConnectToServer.Server;
import acoustically.cloudix.Global;
import acoustically.cloudix.R;

public class AddIOTDeviceActivity extends AppCompatActivity {
  AddIOTDeviceActivity activity = this;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_iot_device);
  }

  public void onClickNext(View view) {
    Global.Obj1 = this;
    HttpConnector connector = new HttpConnector(Server.getUrl("check-device.json"));
    try {

      connector.post(buildJson(), new HttpResponseListener() {
        @Override
        public void HttpResponse(JSONObject json) {
          try {
            if(json.getString("response").equals("success")) {
              if(json.getBoolean("isPasswordDirty")) {
                navigateToNext1(getSerial());
              } else {
                navigateToNext2(getSerial());
              }
            } else {
              Toast.makeText(activity, json.getString("message"), Toast.LENGTH_LONG).show();
            }
          } catch (Exception e) {
            Toast.makeText(activity, "Error occur", Toast.LENGTH_LONG).show();
            Log.e("ERROR", "http request error");
            e.printStackTrace();
          }
        }
      });
    } catch (Exception e) {
      Log.e("ERROR", "http request error");
      Toast.makeText(this, "Error occur", Toast.LENGTH_LONG).show();
    }
  }

  private JSONObject buildJson() throws Exception{
    EditText editTextDeviceSerial = (EditText) findViewById(R.id.editTextDeviceSerial);
    EditText editTextDevicePassword = (EditText) findViewById(R.id.editTextDevicePassword);
    String password = editTextDevicePassword.getText().toString();
    JSONObject json = new JSONObject();
    json.put("serial", getSerial());
    json.put("password", password);
    return json;
  }

  private void navigateToNext1(String serial) {
    Intent intent = new Intent(this, AddIOTDeviceActivity1.class);
    intent.putExtra("serial", serial);
    startActivity(intent);
  }

  private void navigateToNext2(String serial) {
    Intent intent = new Intent(this, AddIOTDeviceActivity2.class);
    intent.putExtra("serial", serial);
    startActivity(intent);
  }

  private String getSerial() {
    Intent intent = getIntent();
    return intent.getStringExtra("serial");
  }
}
