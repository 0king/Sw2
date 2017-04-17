package g.sw2.game_memory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import g.sw2.R;
import me.relex.circleindicator.CircleIndicator;

public class ActivityGameQuestionScreenSlide extends AppCompatActivity {
	
	
	@BindView(R.id.view_pager_non_swipe_question_slide)
	ViewPagerNonSwipeable questionSlidePager;
	@BindView(R.id.circle_indicator_view_pager_question_slide)
	CircleIndicator circleIndicator;
	
	private AdapterViewPagerSlideGame mQuestionSlidePagerAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_question_screen_slide);
		ButterKnife.bind(this);
		
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
