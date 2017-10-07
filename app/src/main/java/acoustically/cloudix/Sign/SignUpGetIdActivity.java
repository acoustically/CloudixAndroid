package acoustically.cloudix.Sign;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import acoustically.cloudix.Global;
import acoustically.cloudix.R;

public class SignUpGetIdActivity extends SignInGetIdActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up_get_id);
  }

  @Override
  public void onClickNext(View view) {
    Global.Obj2 = this;
    EditText editTextId = (EditText)findViewById(R.id.sign_up_id);
    final String id = editTextId.getText().toString();
    try {
      queryToServer(id, "sign-up-id.json", SignUpGetPasswordActivity.class);
    } catch (Exception e) {
      Log.e("ERROR", "Http request failed json");
    }
  }
}
