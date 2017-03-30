package g.sw2.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.MeasureSpec.EXACTLY;

public class BaseLevelBadgeView extends FrameLayout {
    FrameLayout levelGameBadge;

    protected final ShapeDrawable mHexBackground = new ShapeDrawable(new Hexagon());

    public BaseLevelBadgeView(Context context) {
        super(context);
        setBackground(this.mHexBackground);

    }


    public ObjectAnimator createFillingAnimation() {
        HexagonImageView animationView = new HexagonImageView(getContext());
        animationView.setLayoutParams(new LayoutParams(-1, -1));
        animationView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), EXACTLY), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), EXACTLY));
        this.levelGameBadge.addView(animationView, 0);
        animationView.setAnimationBitmap(buildAnimationBitmap());
        animationView.setVerticalAnimationPosition(0.0f);
        return buildFillingAnimator(animationView);
    }

    private Bitmap buildAnimationBitmap() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        List<Integer> animationColors =getAnimationColors();
        LinearLayout animationBars = new LinearLayout(getContext());
        animationBars.setOrientation(LinearLayout.VERTICAL);
        animationBars.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        View disabledTopAnimationView = new View(getContext());
        disabledTopAnimationView.setBackgroundColor(Color.BLUE);

        animationBars.addView(disabledTopAnimationView);disabledTopAnimationView.setLayoutParams(new LayoutParams(-1, height));
        int animationBlockHeight = height / 3;
        for (Integer intValue : animationColors) {
            int color = intValue.intValue();
            View animationBlock = new View(getContext());
            animationBlock.setLayoutParams(new LayoutParams(-1, animationBlockHeight));
            animationBlock.setBackgroundColor(color);
            animationBars.addView(animationBlock);
        }
        View skillGroupColorBottomAnimationView = new View(getContext());
        skillGroupColorBottomAnimationView.setBackgroundColor(Color.BLUE);
        skillGroupColorBottomAnimationView.setLayoutParams(new LayoutParams(-1, animationColors.size() + height));
        animationBars.addView(skillGroupColorBottomAnimationView);
        animationBars.measure(MeasureSpec.makeMeasureSpec(width, EXACTLY), 0);
        animationBars.layout(0, 0, animationBars.getMeasuredWidth(), animationBars.getMeasuredHeight());
        return ImageUtils.viewToBitmap(animationBars, Bitmap.Config.RGB_565);
    }

    private ObjectAnimator buildFillingAnimator(final HexagonImageView animationView) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setTarget(animationView);
        objectAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        objectAnimator.setDuration(1600);
        objectAnimator.setPropertyName("verticalAnimationPosition");
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
               // HomeScreenLevelGameBadgeView.this.levelGameBadge.removeView(animationView);
            }
        });
        return objectAnimator;
    }

    public void setLockedBackgroundColor() {
        this.mHexBackground.getPaint().setColor(Color.GREEN);
    }

    public List<Integer> getAnimationColors() {
        List<Integer> result = new ArrayList();

            result.add(Integer.valueOf(Color.BLUE));

        return result;
    }

}
