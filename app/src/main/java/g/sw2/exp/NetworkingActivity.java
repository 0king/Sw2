package g.sw2.exp;

import android.os.Bundle;
import android.os.Environment;
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
		
		
	}
	
	
	void startFileDownloadFromServer() {//todo delete old data files
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
