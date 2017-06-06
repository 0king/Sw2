package g.sw2.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import g.sw2.R;

/**
 * Created by 5dr on 05/05/17.
 */

public class TimeUtilities {
	
	public static boolean appFirstTimeInstalled(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.prefs_first_time), Context.MODE_PRIVATE);
		boolean result = prefs.getBoolean(Constants.USER_FIRST_INSTALL, true);
		if (result) {
			prefs.edit().putBoolean(Constants.USER_FIRST_INSTALL, false).apply();
		}
		return result;
	}
	
	public static void saveMath10UpdateDate(Context context, String date) {
		SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_MATH10_FILE_UPDATE, Context.MODE_PRIVATE);
		prefs.edit().putString(Constants.MATH10_UPDATE_DATE, date).apply();
	}
	
	public static String getMath10UpdateDate(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_MATH10_FILE_UPDATE, Context.MODE_PRIVATE);
		return prefs.getString(Constants.MATH10_UPDATE_DATE, getTodaysDate());
	}
	
	public static Date getFirstInstallDate(Context context) {
		return new Date();
	}
	
	public static String getTodaysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return sdf.format(new Date());
	}
	
}

