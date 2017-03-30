package g.sw2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityShareApp extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try{
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.setType("text/plain");
			sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Zenius - Smarter way to study");
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
			startActivity(sendIntent);
		}catch (Exception e){
			//...
		}

		/*
       Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
       sharingIntent.setType("text/plain");
       sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "APP NAME (Open it in Google Play Store to Download the Application)");
	   sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, appURL);
       startActivity(Intent.createChooser(sharingIntent, "Share via"));
		*/
	}
}
