package g.sw2.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;


public class ActiveChallengeItemView extends BaseChallengeItemView implements OnTouchListener {
    public BaseLevelBadgeView badgeView;


    public interface Delegate {
        //void challengeTapped(LevelChallenge levelChallenge);

        void lockedChallengeTapped();

        void lockedProChallengeTapped();
    }

    public ActiveChallengeItemView(Context context, float height) {
        super(context, height);
    }

    public void setup(final Delegate delegate) {

        this.badgeView = new BaseLevelBadgeView(getContext());
        this.badgeView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 30, 0, 30);
        this.badgeContainer.setLayoutParams(layoutParams);
        this.badgeContainer.removeAllViews();
        this.badgeContainer.addView(this.badgeView);
        this.badgeContainer.setClickable(true);
        this.badgeContainer.setOnTouchListener(this);
        this.topLineTextView.setOnTouchListener(this);

    }




    private void setVisibilityFadingInIfAppearing(View view, int newState) {
        boolean isAppearing;
        if (view.getVisibility() == View.GONE || newState != 0) {
            isAppearing = false;
        } else {
            isAppearing = true;
        }
        if (isAppearing) {
            view.setAlpha(0.0f);
            view.setVisibility(View.GONE);
            view.animate().alpha(1.0f).start();
            return;
        }
        view.setVisibility(newState);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int masked = event.getActionMasked();
        if (masked == 0) {
            setAlpha(0.7f);
        } else if (masked == 3 || masked == 1) {
            setAlpha(1.0f);
        }
        return false;
    }
}
