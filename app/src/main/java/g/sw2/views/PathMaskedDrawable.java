package g.sw2.views;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

public class PathMaskedDrawable extends Drawable {
    private int height;
    private Paint paint = new Paint();
    private Path path;
    private int width;

    public PathMaskedDrawable(Bitmap bitmap) {
        this.paint.setAntiAlias(true);
        this.paint.setDither(true);
        this.paint.setStyle(Style.FILL);
        setBitmap(bitmap);
    }

    public void setBitmap(Bitmap bitmap) {
        this.paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        setSize(bitmap.getWidth(), bitmap.getHeight());
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(this.path, this.paint);
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        setSize(bounds.width(), bounds.height());
    }

    public void setAlpha(int alpha) {
        if (this.paint.getAlpha() != alpha) {
            this.paint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter cf) {
        this.paint.setColorFilter(cf);
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.width;
    }

    public int getIntrinsicHeight() {
        return this.height;
    }

    public void setFilterBitmap(boolean filter) {
        this.paint.setFilterBitmap(filter);
        invalidateSelf();
    }

    public void setDither(boolean dither) {
        this.paint.setDither(dither);
        invalidateSelf();
    }

    private void setSize(int width, int height) {
        if (width != this.width || height != this.height) {
            this.width = width;
            this.height = height;
            this.path = new Hexagon().createPath((float) (width / 2), (float) (height / 2));
        }
    }
}
