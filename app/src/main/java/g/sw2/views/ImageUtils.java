package g.sw2.views;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ImageUtils {
    public static int DEFAULT_PNG_QUALITY = 100;

    private ImageUtils() {
    }

    public static void writeBitmapAsPng(Bitmap bitmap, File output) {
        try {
            FileOutputStream outputStream = new FileOutputStream(output, false);
            bitmap.compress(CompressFormat.PNG, DEFAULT_PNG_QUALITY, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found with path:" + output.getAbsolutePath(), e);
        } catch (IOException e2) {
            throw new RuntimeException("IOException writing bitmap as png", e2);
        }
    }

    public static Bitmap viewToBitmap(View view, Config config) {
        Bitmap mutableBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), config);
        view.draw(new Canvas(mutableBitmap));
        return mutableBitmap;
    }

    public static Bitmap offscreenViewToBitmap(View view, Config config) {
        view.measure(MeasureSpec.makeMeasureSpec(1920, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(1080, Integer.MIN_VALUE));
        Bitmap mutableBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), config);
        Canvas c = new Canvas(mutableBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(c);
        return mutableBitmap;
    }

    public static Bitmap rasterizeDrawable(Drawable drawable, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}
