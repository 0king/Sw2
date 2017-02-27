package g.sw2;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crittercism.app.Crittercism;
import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;
import com.google.android.gms.analytics.Tracker;

import io.fabric.sdk.android.Fabric;

/**
 * Created by 5dr on 23/02/17.
 */

public class Analytics {

	private static Analytics instance;

	/* google analytics */
	private Tracker gTracker;

	String mixpanelToken = "fc4e9c7385c84486e86a36beebbe01d2";

	private Analytics(){

	}

	public static Analytics get(){
		if(instance == null){
			instance = new Analytics();
		}
		return instance;
	}

	public void init(Context context){

		/* Make sure the *Fabric.with()* line is after all other*3rd-party SDKs that set an *UncaughtExceptionHandler */
		Fabric.with(context.getApplicationContext(), new Crashlytics());


		new ANRWatchDog().setANRListener(new ANRWatchDog.ANRListener() {

			@Override
			public void onAppNotResponding(ANRError arg0) {
				Crashlytics.getInstance().core.logException(arg0);
			}
		}).start();

		Crittercism.initialize(context.getApplicationContext(),"c2dfb4f16e2641c497e1b3927716d3df00555300");


	}






}
