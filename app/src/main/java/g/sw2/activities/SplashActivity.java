package g.sw2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import g.sw2.DataLoader;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		setContentView(R.layout.activity_splash);
		new DataLoader().execute();

		Intent intent = new Intent(this, OnBoardingActivity.class);
		startActivity(intent);
		finish();

	}
}
