package g.sw2.utility;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


/**
 * Created by 5dr on 30/05/17.
 */
public class UpdateCheck extends AsyncTask<Void, Integer, Boolean> {
	
	//1. use interface
	//2. use handler
	//3. get()
	
	boolean available = true;
	String dateStr = "";
	private Context mContext;
	
	public UpdateCheck(Context c) {//provide application context, not activity context
		mContext = c;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		
		File file = new File(Constants.SDCARD_PATH, Constants.LOCAL_FILENAME_MATH10);
		if (!file.exists()) {
			return available;
		}
		
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
			if (dateStr.equals(TimeUtilities.getMath10UpdateDate(mContext))) {
				available = false;
			}
		} else {//handle error
			Toast.makeText(mContext, "Error checking for updates.", Toast.LENGTH_SHORT).show();
		}
		return available;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Boolean available) {
		super.onPostExecute(available);
		if (available) {
			DataManager.startFileDownloadFromServer(mContext, dateStr);
		}
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
}
