package acoustically.cloudix;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.FragmentManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import acoustically.cloudix.AddIOTDevice.AddIOTDeviceActivity;

public class MainActivity extends AppCompatActivity implements DeviceListFragment.OnFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initFragment();
  }

  public void onClickAdd(View view) {
    Intent intent = new Intent(this, AddIOTDeviceActivity.class);
    startActivity(intent);
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  private void initFragment() {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.viewDeviceFrame, new DeviceListFragment());
    fragmentTransaction.commit();
  }

}
