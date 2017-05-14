package g.sw2;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.squareup.leakcanary.LeakCanary;

import g.sw2.database.DatabaseManager;
import g.sw2.database.DbOpenHelper;

/**
 * Created by 5dr on 01/12/16.
 */

public class ZeniusApp extends Application {

	/* start code for google analytics */

	private Tracker mTracker;

	/**
	 * Gets the default {@link Tracker} for this {@link Application}.
	 * @return tracker
	 */
	synchronized public Tracker getDefaultTracker() {
		if (mTracker == null) {
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			// To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
			mTracker = analytics.newTracker(R.xml.global_tracker);
		}
		return mTracker;
	}

	/* end of code for google analytics */


	@Override
	public void onCreate() {
		super.onCreate();
		
		/* Initializing (wrapper around Custom) SQLiteOpenHelper */
		DatabaseManager.initializeInstance(DbOpenHelper.getInstance(getApplicationContext()));
		
		/* for memory leak check - */
		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);
		
		/* for network inspection - */
		//Stetho.initializeWithDefaults(this);
		
		Stetho.initialize(
				Stetho.newInitializerBuilder(this)
						.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
						.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
						.build()
		);

		/*new FlurryAgent.Builder()
				.withLogEnabled(true)
				.build(this,"8XRBMKMWCJBTWHKP3J9V");//.withListener(flurryListener)*/
		
		AndroidNetworking.initialize(getApplicationContext());
	}
}
