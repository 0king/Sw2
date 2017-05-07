package g.sw2.utility;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import g.sw2.database.DatabaseManager;
import g.sw2.database.DbContract;

/**
 * Created by 5dr on 26/04/17.
 */


/* this class is responsible for handling all types of time ops -
* where user is spending time
* per day - how many sessions
* total duration in all sessions
* */
public enum TimeCounter {
	
	INSTANCE;//singleton pattern using enum
	
	private long totalDurationPerDay;//in minutes
	private long startTime;//in minutes
	private long endTime;//in minutes
	
	private long gameTime;//in minutes
	private long studyTime;//only study (of cards) time, excluding end-of-topic exercise
	private long exerciseTime;//in minutes
	
	private long insertedRowId;
	
	public void updateTimeCounter(long timeDuration) {
		new SaveTimeToDatabase().execute(new Long(timeDuration));
	}
	
	private long createEntry(SQLiteDatabase database, long timeDuration) {
		ContentValues values = new ContentValues();
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATE, getDate());
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DURATION, timeDuration);
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATETIME, getDateTime());//yyyy-mm-dd hh:mm:ss.xxxxxx
		values.put(DbContract.TABLE_DAILY_WORKOUT.COLUMN_HISTORY, 1);
		insertedRowId = database.insert(DbContract.TABLE_DAILY_WORKOUT.TABLE_NAME, null, values);
		return insertedRowId;
	}
	
	private String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private class SaveTimeToDatabase extends AsyncTask<Long, Void, Void> {
		@Override
		protected Void doInBackground(Long... params) {
			long timeDuration = params[0];
			SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
			createEntry(database, timeDuration);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void aVoid) {
			//database.close(); Don't close it directly!
			DatabaseManager.getInstance().closeDatabase();
		}
	}
}
