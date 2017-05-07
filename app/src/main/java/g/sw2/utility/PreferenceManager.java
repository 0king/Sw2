package g.sw2.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kushroxx on 26/11/16.
 */


public class PreferenceManager {
    
    public static final String PREF_FIRST_INSTALL = "first_install";
    public static final String PREF_CURRENT_VERSION_FIRST_INSTALL = "version_first_install";
    public static final String PREF_LAUNCH_COUNT = "launch_count";
    public static final String PREF_SOUND_ENABLED = "sound_enabled";
    public static final String PREF_ALREADY_CACHED = "cached";
    
    public static final String PREF_TODAY_DATE = "today_date";
    public static final String PREF_TODAY_DURATION = "today_time_duration";
    public static final String PREF_DB_HAS_CHANGED = "true";
    //public static final String PREF_FIRST_INSTALL_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    
    
    private static PreferenceManager instance;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    
    private PreferenceManager() {
    }
    
    public static PreferenceManager get() {
        if (instance == null) {
            instance = new PreferenceManager();
        }
        return instance;
    }
    
    public void init(Context context) {
        this.preferences = context.getSharedPreferences(context.getPackageName(), 0);
        this.editor = this.preferences.edit();
    }
    
    public void putInt(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.commit();
    }
    
    public int getInt(String key, int defaultValue) {
        return this.preferences.getInt(key, defaultValue);
    }
    
    public void putString(String key, String value) {
        this.editor.putString(key, value);
        this.editor.commit();
    }
    
    public String getString(String key, String defaultValue) {
        return this.preferences.getString(key, defaultValue);
    }
    
    public void putBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }
    
    public boolean getBoolean(String key, boolean defaultValue) {
        return this.preferences.getBoolean(key, defaultValue);
    }
    
    public void putLong(String key, long value) {
        this.editor.putLong(key, value);
        this.editor.commit();
    }
    
    public long getLong(String key, long defaultValue) {
        return this.preferences.getLong(key, defaultValue);
    }
}
