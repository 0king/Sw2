package g.sw2.exp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import g.sw2.R;
import g.sw2.model.Card;

public class NetworkingActivity extends AppCompatActivity {
	
	private final static String COMPLETE_URL = "https://s3.ap-south-1.amazonaws.com/0kingg/math.json";
	private final static String URL = "https://s3.ap-south-1.amazonaws.com";
	private final static String DIRECTORY = Environment.getExternalStorageDirectory().getPath(); //Environment.getExternalStorageDirectory().getPath() == "/mnt/sdcard/";//todo change to sdcard/Android/package_name/data/...
	private final static String FILE = "math10.json";
	private final static String DIRECTORY1 = "0kingg";
	private final static String FILE1 = "math.json";
	private final static String UPDATE_URL = "https://s3.ap-south-1.amazonaws.com/0kingg/file_changed.json";
	private static final int MY_PERMISSIONS_REQUEST_STORAGE = 10101;
	@BindView(R.id.textView3)
	TextView tv1;
	@BindView(R.id.textView4)
	TextView tv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_networking);
		ButterKnife.bind(this);
		
		AndroidNetworking.get(COMPLETE_URL)
				.setPriority(Priority.HIGH)
				.build()
				.getAsJSONArray(new JSONArrayRequestListener() {
					@Override
					public void onResponse(JSONArray response) {
						try {
							tv1.setText(response.getJSONObject(0).getString("chapter_name"));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					
					@Override
					public void onError(ANError anError) {
						tv1.setText(anError.toString());
					}
				});
		
		AndroidNetworking.get(COMPLETE_URL)
				.setPriority(Priority.HIGH)
				.build()
				.getAsObjectList(Card.class, new ParsedRequestListener() {
					@Override
					public void onResponse(Object response) {
						
					}
					
					@Override
					public void onError(ANError anError) {
						
					}
				});
		
		
		if (!writePermissionGranted()) {
			askForWritePermission();
		}
		
	}
	
	private boolean writePermissionGranted() {
		return (ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
	}
	
	private void askForWritePermission() {
		// Should we show an explanation?
		if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) NetworkingActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
			// Show an explanation to the user *asynchronously* -- don't block
			// this thread waiting for the user's response! After the user
			// sees the explanation, try again to request the permission.
		} else {
			// No explanation needed, we can request the permission.
			ActivityCompat.requestPermissions((Activity) NetworkingActivity.this,
					new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
					MY_PERMISSIONS_REQUEST_STORAGE);
			
			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_STORAGE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay! Do the
					// contacts-related task you need to do.
					startFileDownloadFromServer();
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
			}
			// other 'case' lines to check for other
			// permissions this app might request
		}
	}
	
	void startFileDownloadFromServer() {
		AndroidNetworking.download(COMPLETE_URL, DIRECTORY, FILE)
				.setPriority(Priority.HIGH)
				.build()
				.setDownloadProgressListener(new DownloadProgressListener() {
					@Override
					public void onProgress(long bytesDownloaded, long totalBytes) {
						// do anything with progress
					}
				})
				.startDownload(new DownloadListener() {
					@Override
					public void onDownloadComplete() {
						Log.d("durga", "DOwnload COmplete");
					}
					
					@Override
					public void onError(ANError anError) {
						Log.d("durga", anError.toString());
					}
				});
	}
}
