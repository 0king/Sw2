package g.sw2.game_memory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by 5dr on 14/04/17.
 */

public class AdapterViewPagerSlideGame extends FragmentStatePagerAdapter {
	
	private static final int NUM_PAGES = 5;
	private static final String TAG = "Adapter";
	
	public AdapterViewPagerSlideGame(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		//Log.d(TAG,"Position: "+ position);
		return FragmentGameScreen.newInstance(position);
	}
	
	@Override
	public int getCount() {
		return NUM_PAGES;
	}
}
