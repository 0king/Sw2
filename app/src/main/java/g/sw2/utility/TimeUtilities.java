package g.sw2.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import g.sw2.R;

/**
 * Created by 5dr on 05/05/17.
 */

public class TimeUtilities {
	
	public static boolean checkAppFirstTimeInstalled(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.prefs_first_time), Context.MODE_PRIVATE);
		boolean result = prefs.getBoolean("first_install", true);
		if (result) {
			prefs.edit().putBoolean("first_install", false).apply();
		}
		return result;
	}
	
	public static Date getFirstInstallDate(Context context) {
		return new Date();
	}
	
}
