package g.sw2;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.Shape;

public class HexagonPiece extends Shape {
    private static final float END_ROUNDING = 0.3f;
    private static final int NUM_SIDES = 6;
    private final double mAngle = 1.0471975511965976d;
    private int mCornerNumber;
    private int mCornerRadius;
    private float mHexRadius;
    private final int mRotateAngle;
    private final double mSideAngle = 0.10471975511965977d;
    private final int mSidesToDraw;
    private float shiftX = 0.0f;
    private float shiftY = 0.0f;
    private boolean shouldStretch;
    private float[] xJumps = new float[0];
    private float xOffset = 0.0f;
    private float[] yJumps = new float[0];
    private float yOffset = 0.0f;

    private class HexCorner {
        float controlX;
        float controlY;
        private double dTheta;
        float leftX;
        float leftY;
        float rightX;
        float rightY;
        private double theta;

        private HexCorner() {
            this.theta = 1.0471975511965976d * ((double) HexagonPiece.this.mCornerNumber);
            this.dTheta = 0.10471975511965977d;
            this.controlX = ((float) (((double) HexagonPiece.this.mHexRadius) * Math.cos(this.theta))) + HexagonPiece.this.xOffset;
            this.controlY = ((float) (((double) HexagonPiece.this.mHexRadius) * Math.sin(this.theta))) + HexagonPiece.this.yOffset;
            this.rightX = ((float) (((double) HexagonPiece.this.mCornerRadius) * Math.cos(this.theta - this.dTheta))) + HexagonPiece.this.xOffset;
            this.rightY = ((float) (((double) HexagonPiece.this.mCornerRadius) * Math.sin(this.theta - this.dTheta))) + HexagonPiece.this.yOffset;
            this.leftX = ((float) (((double) HexagonPiece.this.mCornerRadius) * Math.cos(this.theta + this.dTheta))) + HexagonPiece.this.xOffset;
            this.leftY = ((float) (((double) HexagonPiece.this.mCornerRadius) * Math.sin(this.theta + this.dTheta))) + HexagonPiece.this.yOffset;
        }

        public boolean isInteriorCorner() {
            return !(HexagonPiece.this.mCornerNumber == 0 || HexagonPiece.this.mCornerNumber == HexagonPiece.this.mSidesToDraw) || HexagonPiece.this.mSidesToDraw == HexagonPiece.NUM_SIDES;
        }
    }

    protected HexagonPiece(int numSidesToDraw, int rotateAngle, boolean shouldStretch) {
        this.mSidesToDraw = numSidesToDraw;
        this.mRotateAngle = rotateAngle;
        this.shouldStretch = shouldStretch;
    }

    public static HexagonPiece createHexagonPiece(int numSidesToDraw, int rotateAngle) {
        return new HexagonPiece(numSidesToDraw, rotateAngle, false);
    }

    public static HexagonPiece createStretchyHexagonPiece(int rotateAngle) {
        return new HexagonPiece(NUM_SIDES, rotateAngle, true);
    }

    public void draw(Canvas canvas, Paint paint) {
        resize((float) canvas.getWidth(), (float) canvas.getHeight());
        canvas.drawPath(this.shouldStretch ? createStretchyPath((float) (canvas.getWidth() / 2), (float) (canvas.getHeight() / 2)) : createPath((float) (canvas.getWidth() / 2), (float) (canvas.getHeight() / 2)), paint);
    }

    public Path createStretchyPath(float centerX, float centerY) {
        this.shouldStretch = true;
        if (centerY > centerX) {
            centerY = centerX * 0.6f;
        }
        this.xJumps = new float[]{0.0f, 10, 10, -((centerX * 2.0f) - (centerY * 2.0f))};
        this.yJumps = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.shiftX =10 / 2.0f;
        this.shiftY = 0.0f;
        return createPath(centerX, centerY);
    }

    public Path createPath(float centerX, float centerY) {
        this.mCornerNumber = 0;
        calculateRadii(centerX, centerY);
        Path polyPath = new Path();
        HexCorner firstCorner = new HexCorner();
        if (isClosedHexagon()) {
            polyPath.moveTo(firstCorner.leftX, firstCorner.leftY);
            if (this.xJumps.length > 0 && this.yJumps.length > 0) {
                polyPath.rMoveTo(this.xJumps[0], this.yJumps[0]);
            }
        } else {
            drawFirstRoundedCorner(polyPath, firstCorner);
        }
        this.mCornerNumber = 1;
        while (this.mCornerNumber <= this.mSidesToDraw) {
            float f;
            HexCorner corner = new HexCorner();
            if (this.xJumps.length > this.mCornerNumber) {
                f = this.xJumps[this.mCornerNumber];
            } else {
                f = 0.0f;
            }
            this.xOffset = f;
            if (this.yJumps.length > this.mCornerNumber) {
                f = this.yJumps[this.mCornerNumber];
            } else {
                f = 0.0f;
            }
            this.yOffset = f;
            polyPath.lineTo(corner.rightX, corner.rightY);
            if (corner.isInteriorCorner()) {
                polyPath.quadTo(corner.controlX, corner.controlY, corner.leftX, corner.leftY);
            } else {
                drawRoundedEndCorner(polyPath, corner);
            }
            this.mCornerNumber++;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) this.mRotateAngle);
        matrix.postTranslate(this.shiftX + centerX, this.shiftY + centerY);
        polyPath.transform(matrix);
        return polyPath;
    }

    private void calculateRadii(float centerX, float centerY) {
        this.mHexRadius = Math.min(centerX, centerY);
        this.mCornerRadius = (int) (((double) this.mHexRadius) * (Math.sin(1.0471975511965979d) / Math.sin(1.9896753472735356d)));
    }

    private boolean isClosedHexagon() {
        return this.mSidesToDraw == NUM_SIDES;
    }

    private void drawFirstRoundedCorner(Path polyPath, HexCorner firstCorner) {
        float partialControlX = blend(firstCorner.leftX, firstCorner.controlX);
        float partialControlY = blend(firstCorner.leftY, firstCorner.controlY);
        polyPath.moveTo(blend(partialControlX, blend(firstCorner.controlX, firstCorner.rightX)), blend(partialControlY, blend(firstCorner.controlY, firstCorner.rightY)));
        polyPath.quadTo(partialControlX, partialControlY, firstCorner.leftX, firstCorner.leftY);
    }

    private void drawRoundedEndCorner(Path polyPath, HexCorner corner) {
        float partialControlX = blend(corner.rightX, corner.controlX);
        float partialControlY = blend(corner.rightY, corner.controlY);
        polyPath.quadTo(partialControlX, partialControlY, blend(partialControlX, blend(corner.controlX, corner.leftX)), blend(partialControlY, blend(corner.controlY, corner.leftY)));
    }

    private float blend(float first, float second) {
        return (0.7f * first) + (END_ROUNDING * second);
    }
}
