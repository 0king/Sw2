package g.sw2.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HexagonImageView extends ImageView {
    private Bitmap animationBitmap;
    private PathMaskedDrawable maskedImageDrawable;

    public HexagonImageView(Context context) {
        this(context, null);
    }

    public HexagonImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setImageDrawable(null);
            return;
        }
        this.maskedImageDrawable = new PathMaskedDrawable(ImageUtils.rasterizeDrawable(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()));
        super.setImageDrawable(this.maskedImageDrawable);
    }

    public void setAnimationBitmap(Bitmap animationBitmap) {
        this.animationBitmap = animationBitmap;
    }

    public void setVerticalAnimationPosition(float position) {
        if (this.animationBitmap != null) {
            try {
                int height = getMeasuredHeight();
                float windowsInWhole = ((float) this.animationBitmap.getHeight()) / ((float) height);
                if (windowsInWhole < 1.0f) {
                    throw new RuntimeException("Cannot animate bitmap smaller than view");
                }
                Bitmap displayedBitmap = Bitmap.createBitmap(this.animationBitmap, 0, (int) (((windowsInWhole - 1.0f) * position) * ((float) height)), getMeasuredWidth(), height);
                if (this.maskedImageDrawable == null) {
                    setImageDrawable(new BitmapDrawable(getResources(), displayedBitmap));
                } else {
                    this.maskedImageDrawable.setBitmap(displayedBitmap);
                    this.maskedImageDrawable.invalidateSelf();
                }
                if (position == 1.0f) {
                    this.animationBitmap = null;
                }
            } catch (OutOfMemoryError e) {
                this.animationBitmap = null;
            } catch (IllegalArgumentException e2) {
                //Timber.e(e2, "Could not build bitmap", new Object[0]);
                this.animationBitmap = null;
            }
        }
    }
}
