package g.sw2.other;

/**
 * Created by 5dr on 21/10/16.
 */

public class CircleTransform {//extends BitmapTransformation
	
	/* using Glide */

	/*public CircleTransform(Context context) {
		super(context);
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
		return circleCrop(pool, toTransform);
	}

	private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
		if (source == null) return null;

		int size = Math.min(source.getWidth(), source.getHeight());
		int x = (source.getWidth() - size) / 2;
		int y = (source.getHeight() - size) / 2;

		Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

		Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
		if (result == null) {
			result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
		paint.setAntiAlias(true);
		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);
		return result;
	}

	@Override
	public String getId() {
		return getClass().getName();
	}
*/
}
