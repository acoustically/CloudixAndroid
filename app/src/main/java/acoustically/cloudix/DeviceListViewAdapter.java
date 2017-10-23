package acoustically.cloudix;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import acoustically.cloudix.ConnectToServer.HttpRequestor;
import acoustically.cloudix.ConnectToServer.HttpResponseListener;
import acoustically.cloudix.ConnectToServer.JSONObjectWithToken;
import acoustically.cloudix.ConnectToServer.Server;

/**
 * Created by acoustically on 17. 10. 11.
 */

public class DeviceListViewAdapter extends BaseAdapter implements View.OnClickListener {
  List<DeviceListItem> deviceList = new LinkedList<>();
  Fragment fragment;

  public DeviceListViewAdapter(Fragment fragment) {
    this.fragment = fragment;
  }

  public void addDevice(DeviceListItem item) {
    deviceList.add(item);
  }
  @Override
  public int getCount() {
    return deviceList.size();
  }

  @Override
  public Object getItem(int i) {
    return deviceList.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View view, final ViewGroup viewGroup) {
    Context context = viewGroup.getContext();
    final DeviceListItem device = deviceList.get(i);

    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.device_list_item, viewGroup, false);

    TextView deviceNameView = (TextView) view.findViewById(R.id.DeviceName);
    deviceNameView.setText(deviceList.get(i).getName());
    Button devicePowerButton = (Button) view.findViewById(R.id.DevicePower);
    devicePowerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          HttpRequestor requestor = new HttpRequestor(Server.getUrl("switchs/turn.json"));
          JSONObject json = new JSONObjectWithToken();
          json.put("serial", device.getSerial());
          json.put("position", device.getPosition());
          if(device.isPowerOn()) {
            json.put("power", 0);
          } else {
            json.put("power", 1);
          }
          requestor.post(json, new HttpResponseListener() {
            @Override
            protected void httpResponse(JSONObject json) {
              try {
                if (json.getString("response").equals("success")) {
                  fragment.onResume();
                } else {
                  Toast.makeText(viewGroup.getContext(), json.getString("message"), Toast.LENGTH_LONG).show();
                }
              } catch (Exception e) {
                Log.e("ERROR", "Error is occurred in turn switch method at receive response");
              }
            }

            @Override
            protected void httpExcepted() {

            }
          });
        } catch (Exception e) {
          Log.e("ERROR", "error is occurred at switch on of");
        }
      }
    });
    if(device.isPowerOn()) {
      devicePowerButton.setBackground(context.getDrawable(R.drawable.on));
    } else {
      devicePowerButton.setBackground(context.getDrawable(R.drawable.off));
    }
    return view;
  }

  @Override
  public void onClick(View view) {
    
  }
}
