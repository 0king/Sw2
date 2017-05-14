package g.sw2.memory_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import g.sw2.R;
import me.relex.circleindicator.CircleIndicator;

public class ActivityGameScreenSlide extends FragmentActivity {
	
	
	@BindView(R.id.viewpager_nonswipe_activity_game_screen_slide)
	ViewPagerNonSwipeable nonSwipeableViewPager;
	@BindView(R.id.view_pager_circle_indicator)
	CircleIndicator circleIndicator;
	Handler slideRepeatHandler;
	
	private AdapterViewPagerSlideGame mScreenSlidePagerAdapter;
	
	private Unbinder unbinder;//for ButterKnife
	private Runnable next = new Runnable() {
		@Override
		public void run() {
			//Log.d("Zenius","aa gelu: ");
			moveToNextPage();
			slideRepeatHandler.postDelayed(this, 3000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen_slide);
		ButterKnife.bind(this);
		
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



