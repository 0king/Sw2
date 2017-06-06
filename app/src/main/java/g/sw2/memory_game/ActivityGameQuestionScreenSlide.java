package g.sw2.memory_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;

import g.sw2.R;
import me.relex.circleindicator.CircleIndicator;

public class ActivityGameQuestionScreenSlide extends AppCompatActivity {
	
	
	ViewPagerNonSwipeable questionSlidePager;
	CircleIndicator circleIndicator;
	
	private AdapterViewPagerSlideGame mQuestionSlidePagerAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_question_screen_slide);
		
		questionSlidePager = (ViewPagerNonSwipeable) findViewById(R.id.view_pager_non_swipe_question_slide);
		circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator_view_pager_question_slide);
		
		mQuestionSlidePagerAdapter = new AdapterViewPagerSlideGame(getSupportFragmentManager());
		questionSlidePager.setAdapter(mQuestionSlidePagerAdapter);
		questionSlidePager.setPageTransformer(true, new RotateDownTransformer());
		circleIndicator.setViewPager(questionSlidePager);
	}
	
	void moveToNextQuestionPage() {
		if (questionSlidePager.getCurrentItem() < questionSlidePager.getAdapter().getCount() - 1)
			questionSlidePager.setCurrentItem(questionSlidePager.getCurrentItem() + 1);
		else {
			startActivity(new Intent(ActivityGameQuestionScreenSlide.this, ActivityMemoryGameResult.class));
			finish();
		}
	}
}
