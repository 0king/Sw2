package g.sw2.utility;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by 5dr on 05/12/16.
 */

public class FontManager {


	private static FontManager instance;
	
	private Typeface oxin;//fades in top
	
	
	



	private FontManager() {
	}
	
	public static FontManager get() {
		if (instance == null) {
			instance = new FontManager();
		}
		return instance;
	}
	
	
	
	public Typeface getFontOxin() {
		return this.oxin;
	}
	
	

	public void initFonts(Context context) {
		this.oxin = Typeface.createFromAsset(context.getAssets(), "fonts/oxin.ttf");
	}


}