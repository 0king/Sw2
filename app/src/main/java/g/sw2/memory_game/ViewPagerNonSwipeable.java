package g.sw2.memory_game;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by 5dr on 13/04/17.
 */

public class ViewPagerNonSwipeable extends ViewPager {
	
	private FixedSpeedScroller mScroller = null;
	
	public ViewPagerNonSwipeable(Context context) {
		super(context);
		init();
	}
	
	public ViewPagerNonSwipeable(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return false;
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
	
	private void init() {
		try {
			Class<?> viewpager = ViewPager.class;
			Field scroller = viewpager.getDeclaredField("mScroller");
			scroller.setAccessible(true);
			mScroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator());
			scroller.set(this, mScroller);
		} catch (Exception ignored) {
		}
	}
	
	public void setScrollDuration(int duration) {
		mScroller.setScrollDuration(duration);
	}
	
	private class FixedSpeedScroller extends Scroller {
		
		private int mDuration = 600;
		
		public FixedSpeedScroller(Context context) {
			super(context);
		}
		
		public FixedSpeedScroller(Context context, Interpolator interpolator) {
			super(context, interpolator);
		}
		
		public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
			super(context, interpolator, flywheel);
		}
		
		@Override
		public void startScroll(int startX, int startY, int dx, int dy, int duration) {
			// Ignore received duration, use fixed one instead
			super.startScroll(startX, startY, dx, dy, mDuration);
		}
		
		@Override
		public void startScroll(int startX, int startY, int dx, int dy) {
			// Ignore received duration, use fixed one instead
			super.startScroll(startX, startY, dx, dy, mDuration);
		}
		
		public void setScrollDuration(int duration) {
			mDuration = duration;
		}
	}
	
}
