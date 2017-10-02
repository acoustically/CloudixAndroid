package acoustically.cloudix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpGetID extends SignInGetID {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up_get_id);
  }

  @Override
  public void onClickNext(View view) {
    EditText editTextId = (EditText)findViewById(R.id.sign_up_id);
    final String id = editTextId.getText().toString();
    try {
      queryToServer(id, "sign-up-id.json", SignUpGetPassword.class);
    } catch (Exception e) {
      Log.e("ERROR", "Http request failed json");
    }
  }
}
