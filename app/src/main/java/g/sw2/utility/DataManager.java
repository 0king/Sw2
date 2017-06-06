package g.sw2.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import java.io.File;

/**
 * Created by 5dr on 19/05/17.
 */

public class DataManager {
	
	
	private static boolean ifMathUpdateAvailable(final Context context) {
		boolean available = true;
		
		return available;
	}
	
	//making synchronous request (execute it on a diff thread)
	private static boolean ifMathUpdateAvailable2(final Context context) {
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
			if (dateStr.equals(TimeUtilities.getMath10UpdateDate(context))) {
				available = false;
			}
		} else {//handle error
			Toast.makeText(context, "Error checking for updates.", Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setTitle("Update check failed").setMessage(response.getError() + " Retry?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					ifMathUpdateAvailable2(context);
				}
			}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).show();
		}
		return available;
	}
	
	public static void startFileDownloadFromServer(final Context context, final String date) {
		
		// delete old file before downloading
		File file = new File(Constants.SDCARD_PATH, Constants.LOCAL_FILENAME_MATH10);
		if (file.exists()) {
			file.delete();
		}
		
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
						TimeUtilities.saveMath10UpdateDate(context, date);
					}
					
					@Override
					public void onError(ANError anError) {
						Toast.makeText(context, "Error downloading data.", Toast.LENGTH_LONG).show();
					}
				});
	}
	
	private static void startFileDownloadFromServer2(final Context context) {
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
						//TimeUtilities.saveMath10UpdateDate(context);
					}
					
					@Override
					public void onError(ANError anError) {
						Toast.makeText(context, "Error downloading data.", Toast.LENGTH_LONG).show();
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
						alertDialogBuilder.setTitle("Download failed").setMessage(anError.getMessage() + " Retry?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								//startFileDownloadFromServer(context);
							}
						}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						}).show();
						
					}
				});
	}
	
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}
	
	private class UpdateCheck extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Boolean aBoolean) {
			super.onPostExecute(aBoolean);
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}
	
}
