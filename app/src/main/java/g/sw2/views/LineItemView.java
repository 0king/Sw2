package g.sw2.views;

import android.content.Context;

public class LineItemView extends BaseChallengeItemView {
    public LineItemView(Context context, float height) {
        super(context, height);
    }

    public void setLineEnabled(boolean enabled) {
        setBottomStrokeEnabled(enabled);
        setTopStrokeEnabled(enabled);
    }
}
