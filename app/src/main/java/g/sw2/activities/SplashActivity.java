package g.sw2.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import g.sw2.utility.Constants;
import g.sw2.utility.TimeUtilities;
import g.sw2.utility.UpdateCheck;

public class SplashActivity extends AppCompatActivity {
	
	//private boolean updateAvailable=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= 23) { //for sdk>23, ask for permissions
			if (!writePermissionGranted())
				askForWritePermission();
			else {
				if (TimeUtilities.appFirstTimeInstalled(getBaseContext())) {
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
						}
					}, 1000);
				} else {
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							startActivity(new Intent(SplashActivity.this, MainActivity.class));
						}
					}, 1000);
				}
				//update check and download data
				new UpdateCheck(getApplicationContext()).execute();
			}
		} else {
			if (TimeUtilities.appFirstTimeInstalled(getBaseContext())) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
					}
				}, 1000);
			} else {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
					}
				}, 1000);
			}
			//update check and download data
			new UpdateCheck(getApplicationContext()).execute();
		}
		
	}
	
	private boolean writePermissionGranted() {
		return (ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
	}
	
	private void askForWritePermission() {
		// Should we show an explanation?
		if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) SplashActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
			// Show an explanation to the user *asynchronously* -- don't block
			// this thread waiting for the user's response! After the user
			// sees the explanation, try again to request the permission.
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("Permission required!")
					.setMessage("Please grant permiison, Without it app can't work :(")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions((Activity) SplashActivity.this,
									new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
									Constants.PERMISSION_REQUEST_SD_STORAGE);
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			}).show();
		} else {
			// No explanation needed, we can request the permission.
			ActivityCompat.requestPermissions((Activity) SplashActivity.this,
					new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
					Constants.PERMISSION_REQUEST_SD_STORAGE);
			
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case Constants.PERMISSION_REQUEST_SD_STORAGE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay! Do the
					// contacts-related task you need to do.
					
					new UpdateCheck(getApplicationContext()).execute();
					
					if (TimeUtilities.appFirstTimeInstalled(getBaseContext())) {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
							}
						}, 2000);
					} else {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								startActivity(new Intent(SplashActivity.this, MainActivity.class));
							}
						}, 2000);
					}
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					Toast.makeText(getBaseContext(), "Please grant write permission", Toast.LENGTH_LONG).show();
					finish();
				}
			}
			// other 'case' lines to check for other
			// permissions this app might request
		}
	}
	
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}
	
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) ||
				    Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
}
