package g.sw2.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import g.sw2.R;


public abstract class BaseChallengeItemView extends FrameLayout {
    //@BindView(R.id.badge_content_layout)
    ViewGroup badgeContainer ;
    @BindView(R.id.bottom_progress_segment)
    View bottomProgressSegment;
    @BindView(R.id.badge_text_view)
    TextView topLineTextView;
    @BindView(R.id.top_progress_segment)
    View topProgressSegment;

    public BaseChallengeItemView(Context context, float height) {
        super(context);

        Log.d("Zenius","IN Basechallenge");
        LayoutInflater.from(context).inflate(R.layout.hexagon_layout, this);
        ButterKnife.bind(this);
        setLayoutParams(new LayoutParams(-1, (int) height));
    }

    public void setTopStrokeEnabled(boolean enabled) {
        setStrokeItemBackgroundColor(this.topProgressSegment, enabled);
    }

    public void setBottomStrokeEnabled(boolean enabled) {
        setStrokeItemBackgroundColor(this.bottomProgressSegment, enabled);
    }

    private void setStrokeItemBackgroundColor(View strokeContainerView, boolean enabled) {
        //ButterKnife.findById(strokeContainerView, 2131559134).setBackgroundColor(getResources().getColor(enabled ? 2131492998 : 2131493034));
    }
}
