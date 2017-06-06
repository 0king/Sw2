package g.sw2.memory_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import g.sw2.R;
import me.relex.circleindicator.CircleIndicator;

public class ActivityGameScreenSlide extends FragmentActivity {
	
	ViewPagerNonSwipeable nonSwipeableViewPager;
	CircleIndicator circleIndicator;
	Handler slideRepeatHandler;
	
	private AdapterViewPagerSlideGame mScreenSlidePagerAdapter;
	
	private Runnable next = new Runnable() {
		@Override
		public void run() {
			moveToNextPage();
			slideRepeatHandler.postDelayed(this, 3000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen_slide);
		
		nonSwipeableViewPager = (ViewPagerNonSwipeable) findViewById(R.id.viewpager_nonswipe_activity_game_screen_slide);
		circleIndicator = (CircleIndicator) findViewById(R.id.view_pager_circle_indicator);
		
		mScreenSlidePagerAdapter = new AdapterViewPagerSlideGame(getSupportFragmentManager());
		nonSwipeableViewPager.setAdapter(mScreenSlidePagerAdapter);
		nonSwipeableViewPager.setPageTransformer(true, new CubeOutTransformer());
		circleIndicator.setViewPager(nonSwipeableViewPager);
		
		slideRepeatHandler = new Handler();
		slideRepeatHandler.postDelayed(next, 2000);
		
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	void moveToNextPage() {
		if (nonSwipeableViewPager.getCurrentItem() < nonSwipeableViewPager.getAdapter().getCount() - 1)
			nonSwipeableViewPager.setCurrentItem(nonSwipeableViewPager.getCurrentItem() + 1);
		else {
			startActivity(new Intent(ActivityGameScreenSlide.this, ActivityGameQuestionScreenSlide.class));
			finish();
		}
	}
	
	
	
	
	/*@Override
	public void onDestroy() {
		super.onDestroy();
		unbinder.unbind();//reset in case of fragments
	}*/
	
}



