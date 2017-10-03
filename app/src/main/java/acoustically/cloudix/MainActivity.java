package acoustically.cloudix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import acoustically.cloudix.AddIOTDevice.AddIOTDeviceActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onClickAdd(View view) {
    Intent intent = new Intent(this, AddIOTDeviceActivity.class);
    startActivity(intent);
  }
}
