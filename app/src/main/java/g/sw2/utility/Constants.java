package g.sw2.utility;

import android.os.Environment;

/**
 * Created by 5dr on 18/05/17.
 */

public class Constants {
	
	public final static int PERMISSION_REQUEST_SD_STORAGE = 10;
	public final static String MATH10_AWS_URL = "https://s3.ap-south-1.amazonaws.com/0kingg/math10.json";
	public final static String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath(); //Environment.getExternalStorageDirectory().getPath() == "/mnt/sdcard/";//todo change to sdcard/Android/package_name/data/...
	public final static String MATH_UPDATE_URL = "https://s3.ap-south-1.amazonaws.com/0kingg/file_changed.json";
	public final static String LOCAL_FILENAME_MATH10 = "math10";
	
	public final static String PREFS_FIRST_TIME = "com.getzenius.zenius.shared_preference_first_time_data";//shared prefs file name
	public static final String USER_FIRST_INSTALL = "first_install";
	public static final String USER_FIRST_VISIT = "first_visit";
	
	public static final String PREFS_MATH10_FILE_UPDATE = "com.getzenius.zenius.shared_preference_math10_update";//shared prefs file name
	public static final String MATH10_UPDATE_DATE = "math10_last_update_date";
	
	
}
