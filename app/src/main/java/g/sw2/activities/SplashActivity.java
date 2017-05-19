package g.sw2.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;

import org.json.JSONException;
import org.json.JSONObject;

import g.sw2.utility.Constants;
import g.sw2.utility.TimeUtilities;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* ask for write permissions */
		if (!writePermissionGranted()) {
			askForWritePermission();
		}
		
		
		if (TimeUtilities.checkAppFirstTimeInstalled(getBaseContext())) {
			Intent intent = new Intent(this, OnBoardingActivity.class);
			intent.putExtra("download_data_from_server", true);
			startActivity(intent);
			finish();
			//for first time, data downloading will be started in Onboarding activity, for fast onboarding
		} else {
			if (isNetworkAvailable()) {
				if (ifMathUpdateAvailable()) {
					//todo delete the prev file, or it'll be replaced by new file with same name?
					startFileDownloadFromServer();
				}
				//else (no update available) the file will be loaded from sd card
			}
			//else if network not available, inform user about that in main-activity
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}
	}
	
	boolean ifMathUpdateAvailable() {
		boolean available = true;
		String dateStr = "";
		ANRequest request = AndroidNetworking.get(Constants.MATH_UPDATE_URL)
				                    .setPriority(Priority.HIGH)
				                    .build();
		ANResponse<JSONObject> response = request.executeForJSONObject();//making synchronous request
		if (response.isSuccess()) {
			JSONObject obj = response.getResult();
			try {
				dateStr = obj.getString("change_date");
			} catch (JSONException e) {
				throw new RuntimeException(e);
			}
			if (dateStr.equals(TimeUtilities.getMath10UpdateDate(getBaseContext()))) {
				available = false;
			}
		} else {//handle error
			Toast.makeText(SplashActivity.this.getBaseContext(), "Error checking for updates. Retrying...", Toast.LENGTH_SHORT).show();
			ifMathUpdateAvailable();
		}
		return available;
	}
	
	void startFileDownloadFromServer() {
		AndroidNetworking.download(Constants.MATH10_AWS_URL, Constants.SDCARD_PATH, Constants.LOCAL_FILENAME_MATH10)//this happens asynchronously
				.setPriority(Priority.HIGH)
				.build()
				.setDownloadProgressListener(new DownloadProgressListener() {
					@Override
					public void onProgress(long bytesDownloaded, long totalBytes) {
					}
				})
				.startDownload(new DownloadListener() {
					@Override
					public void onDownloadComplete() {
						TimeUtilities.saveMath10UpdateDate(SplashActivity.this.getBaseContext());
					}
					
					@Override
					public void onError(ANError anError) {
						Toast.makeText(SplashActivity.this.getBaseContext(), "Error downloading data. Retrying...", Toast.LENGTH_LONG).show();
						startFileDownloadFromServer();
					}
				});
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
		} else {
			// No explanation needed, we can request the permission.
			ActivityCompat.requestPermissions((Activity) SplashActivity.this,
					new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
					Constants.PERMISSION_REQUEST_SD_STORAGE);
			
			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.
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
					startFileDownloadFromServer();
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					Toast.makeText(getBaseContext(), "Grant write permission", Toast.LENGTH_LONG).show();
				}
			}
			// other 'case' lines to check for other
			// permissions this app might request
		}
	}
	
	boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
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
