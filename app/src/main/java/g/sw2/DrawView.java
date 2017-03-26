package g.sw2;

/**
 * Created by Kush Agrawal on 3/5/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    Paint whitePaint = new Paint();
    Path path = new Path();
    private Hexagon hexagon = new Hexagon();

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.RED);
        //paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.FILL);
        paint.setFlags(1);

        whitePaint.setColor(-1);
        //paint.setStrokeWidth(10f);
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setFlags(1);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        float centerY = 0.0f;
        for (int i=2;i>0;i--) {

            centerY = (((float) canvas.getHeight()) / 2.0f) + ((float) ((i - 1) * 30));
            if(i>=0) {
                canvas.drawPath(hexagon.createPath(((float) canvas.getWidth()) / 2.0f, 2.0f + centerY), whitePaint);
            }
            canvas.drawPath(this.hexagon.createPath(((float) canvas.getWidth()) / 2.0f, centerY), paint);

        }

    }

}
