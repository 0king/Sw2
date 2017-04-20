package g.sw2.utility;

import android.content.Context;
import android.media.MediaPlayer;

import g.sw2.R;

/**
 * Created by 5dr on 17/04/17.
 */

public class ContentManager {
	
	private static ContentManager instance;
	
	/**
	 * The Static initializer constructs the instance at class
	 * loading time; this is to simulate a more involved
	 * construction process (it it were really simple, you'd just
	 * use an initializer)
	 */
	static {
		instance = new ContentManager();
	}
	
	private ContentManager() {
		// nothing to do this time
	}
	
	/**
	 * Static 'instance' method
	 */
	public static ContentManager getInstance() {
		return instance;
	}
	
	
	public void loadSessionData() {
		
	}
	
	
	public void playButtonClickSound(Context context) {
		MediaPlayer.create(context, R.raw.button_click).start();
	}
	
	public void playCorrectAnswerSound(Context context) {
		MediaPlayer.create(context, R.raw.answer_correct).start();
	}
	
	public void playIncorrectAnswerSound(Context context) {
		MediaPlayer.create(context, R.raw.answer_incorrect).start();
	}
	
	public void playCoinsSound(Context context) {
		MediaPlayer.create(context, R.raw.coins).start();
	}
	
	
}
