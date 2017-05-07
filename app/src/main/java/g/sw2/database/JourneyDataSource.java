package g.sw2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 5dr on 25/04/17.
 */


//This class is our DAO. It maintains the database connection and supports adding new comments and fetching all comments.
public class JourneyDataSource {
	
	private DbOpenHelper dbHelper;
	private long newRowId;
	private long insertedRowId;
	private String[] allColumns = {DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATETIME,
			DbContract.TABLE_DAILY_WORKOUT.COLUMN_DURATION};
	
	public long createEntry1(SQLiteDatabase database, int timeDuration) {
		ContentValues values = new ContentValues();
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATETIME, getDateTime());//yyyy-mm-dd hh:mm:ss.xxxxxx
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DURATION, timeDuration);
		insertedRowId = database.insert(DbContract.TABLE_DAILY_WORKOUT.TABLE_NAME, null, values);
		return insertedRowId;
	}
	
	public DailyLog createEntry2(SQLiteDatabase database, int timeDuration) {
		ContentValues values = new ContentValues();
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATETIME, getDateTime());//yyyy-mm-dd hh:mm:ss.xxxxxx
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DURATION, timeDuration);
		insertedRowId = database.insert(DbContract.TABLE_DAILY_WORKOUT.TABLE_NAME, null, values);
		Cursor cursor = database.query(DbContract.TABLE_DAILY_WORKOUT.TABLE_NAME,
				allColumns, DbContract.TABLE_DAILY_WORKOUT.COLUMN_ID + " = " + insertedRowId, null, null, null, null);
		cursor.moveToFirst();
		DailyLog dailyLog = cursorToDailyLog(cursor);
		cursor.close();
		return dailyLog;
	}
	
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private DailyLog cursorToDailyLog(Cursor cursor) {
		DailyLog dailyLog = new DailyLog();
		dailyLog.setDate(cursor.getString(1));
		dailyLog.setDuration(cursor.getLong(3));
		return dailyLog;
	}
	
	
}
