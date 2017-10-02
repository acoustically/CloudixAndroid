package acoustically.cloudix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_index);
  }

  public void onClickSignUp(View view) {
    Intent intent = new Intent(this, SignUpGetID.class);
    startActivity(intent);
    finish();
  }

  public void onClickSignIn(View view) {
    Intent intent = new Intent(this, SignInGetID.class);
    startActivity(intent);
    finish();
  }
}
