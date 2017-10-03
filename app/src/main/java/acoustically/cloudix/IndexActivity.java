package acoustically.cloudix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import acoustically.cloudix.Sign.SignInGetIdActivity;
import acoustically.cloudix.Sign.SignUpGetIdActivity;

public class IndexActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_index);
    Global.Obj1 = this;
  }

  public void onClickSignUp(View view) {
    Intent intent = new Intent(this, SignUpGetIdActivity.class);
    startActivity(intent);
  }

  public void onClickSignIn(View view) {
    Intent intent = new Intent(this, SignInGetIdActivity.class);
    startActivity(intent);
  }
}
