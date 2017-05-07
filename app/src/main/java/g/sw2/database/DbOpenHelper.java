package g.sw2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 5dr on 25/04/17.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
	//using singleton pattern to avoid multiple instances
	
	private static DbOpenHelper INSTANCE = null;
	private Context mContext;
	
	private DbOpenHelper(Context context) {
		super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
		this.mContext = context;
	}
	
	public static DbOpenHelper getInstance(Context context) {
		/*use the application context as suggested by CommonsWare.
		 * this will ensure that you dont accidentally leak an Activitys
         * context
         */
		if (INSTANCE == null) {
			INSTANCE = new DbOpenHelper(context.getApplicationContext());
		}
		return INSTANCE;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DbContract.TABLE_DAILY_WORKOUT.CREATE_TABLE_JOURNEY);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DbContract.TABLE_DAILY_WORKOUT.DELETE_TABLE_JOURNEY);
		onCreate(db);
	}
}
