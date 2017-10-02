package acoustically.cloudix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpGetPassword extends SignInGetPassword {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up_get_password);
  }
  @Override
  public void onClickSign(View view) {
    EditText editTextPassword = (EditText)findViewById(R.id.sign_up_password);
    String password = editTextPassword.getText().toString();
    Intent intent = getIntent();
    String id = intent.getStringExtra("data");
    try {
      qeuryToServer(id, password, "sign-up.json");
    } catch (Exception e) {
      Log.e("ERROR", "http request failed - json");
    }
  }
}
