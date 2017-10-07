package acoustically.cloudix.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import acoustically.cloudix.Global;
import acoustically.cloudix.R;

public class SignUpGetPasswordActivity extends SignInGetPasswordActivity {
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
      qeuryToServer(id, password, "sign/up.json");
    } catch (Exception e) {
      Log.e("ERROR", "http request failed - json");
    }
  }
}
