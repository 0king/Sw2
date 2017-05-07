package g.sw2.utility;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import g.sw2.database.DatabaseManager;
import g.sw2.database.DbContract;

/**
 * Created by 5dr on 02/05/17.
 */

public enum QueryDb {
	INSTANCE;//singleton pattern using enum
	
	private static final String TAG = QueryDb.class.getSimpleName();
	
	long todaysTotalTime;//in minutes
	
	
	public List<String> getAllHistory() {
		List<String> dates = new ArrayList<>();
		SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
		String[] columns = {DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATE, DbContract.TABLE_DAILY_WORKOUT.COLUMN_HISTORY};
		String selection = "COUNT(" + DbContract.TABLE_DAILY_WORKOUT.COLUMN_HISTORY + ")";
		String groupBy = DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATE;
		String sortOrder = DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATE + " DESC";//ASC
		
/*		Cursor cursor = db.query(DbContract.TABLE_DAILY_WORKOUT.TABLE_NAME,
				columns,
				selection,   //selection (where clause)
				null,   //no selection args (=...)
				groupBy,
				null, //filter group
				null //sort order
				);*/
		
		Cursor cursor = db.rawQuery("SELECT COUNT(history), date FROM daily_journey_details GROUP BY date", null);//todo use cursor.query (protects from sql injection) instead of rawQuery
		//queryNumEntries long queryNumEntries (SQLiteDatabase db,String table) - Query the table for the number of rows in the table.
		
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					dates.add(cursor.getString(cursor.getColumnIndex(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATE)));
				} while (cursor.moveToNext());
			}
			cursor.close();
		}
		return dates;
	}
	
	public List<Long> getTimeDurationLast14days() {
		List<Long> times = new ArrayList<>();
		return times;
	}
	
	public HashMap<String, Long> getAllTimeDurationPerDay() {
		HashMap<String, Long> values = new HashMap<>();//default value =0
		SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
		Cursor cursor = db.rawQuery("SELECT date, TOTAL(time_duration) FROM daily_journey_details GROUP BY date", null);//todo use LIMIT to query only last n-th rows
		if (cursor.moveToFirst()) {
			do {
				values.put(cursor.getString(0), cursor.getLong(1));
			}
			while (cursor.moveToNext());
			cursor.close();
		} else
			Log.d("durga", "empty cursor");
		return values;
	}
	
	public void getHistory(Date startDate, Date endDate) {
	}
	
	public void getTimeDuration(Date startDate, Date endDate) {
	}
	
	public long getTodaysTotalTime() {//reuturns time in minutes
		new GetTotalTimeToday().execute();
		return todaysTotalTime;
	}
	
	public int getTodaysTotalNumberOfSessions() {
		int totalDays = 0;
		return totalDays;
	}
	
	public long getTotalTime(Date date) {
		long totalTime = 0;
		return totalTime;
	}
	
	public int getTotalNumberOfSessions(Date date) {
		int totalDays = 0;
		return totalDays;
	}
	
	public List<Integer> getHistory() {
		ArrayList<Integer> x = new ArrayList<>();
		return x;
	}
	
	public List<Integer> getMonthlyHistory(String monthName) {
		ArrayList<Integer> x = new ArrayList<>();
		return x;
	}
	
	public long getWeeksTotalTime(String week) {
		long totalTime = 0;
		return totalTime;
	}
	
	public long getMonthsTotalTime(String month) {
		long totalTime = 0;
		return totalTime;
	}
	
	public int getMonthsTotalNumberOfDaysStudied(String month) {
		int totalDays = 0;
		return totalDays;
	}
	
	public int getWeeksFrequency(String week) {
		/* Number of days done in a week */
		int x = 0;
		return x;
	}
	
	public int getMonthsFrequency(String month) {
		/* Number of days done in a month */
		int x = 0;
		return x;
	}
	
	private long getTotalTimeToday(SQLiteDatabase database) {
		long l = 0;
		Cursor cursor = database.rawQuery("SELECT date, TOTAL(time_duration) FROM daily_journey_details WHERE date = ? GROUP BY date", new String[]{getDate()});
		if (cursor.moveToFirst()) {
			l = cursor.getLong(1);//A cursor from a SQLite database in Android references columns from 0; Returns the zero-based index for the given column name, or -1 if the column doesn't exist.
			Log.d("durga", String.valueOf(l));
		}
		cursor.close();
		return l;
	}
	
	private long getTotalTimeToday2(SQLiteDatabase database) {
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				DbContract.TABLE_DAILY_WORKOUT.COLUMN_DURATION//,
				//DbContract.TABLE_DAILY_WORKOUT.COLUMN_CREATED_AT,
				//DbContract.TABLE_DAILY_WORKOUT.COLUMN_ID
		};
		// Filter results WHERE "title" = 'My Title'
		String selection = DbContract.TABLE_DAILY_WORKOUT.COLUMN_DATE + " = ?";
		String[] selectionArgs = {getDate()};
		// How you want the results sorted in the resulting Cursor
		String sortOrder = DbContract.TABLE_DAILY_WORKOUT.COLUMN_CREATED_AT + " DESC";
		
		Cursor cursor = database.query(
				DbContract.TABLE_DAILY_WORKOUT.TABLE_NAME,  // The table to query
				projection,                               // The columns to return
				selection,                                // The columns for the WHERE clause
				selectionArgs,                            // The values for the WHERE clause
				null,                                     // don't group the rows
				null,                                     // don't filter by row groups
				null                                      // The sort order
		);
		
		if (cursor == null)
			Log.d(TAG, "cursor is null");
		
		cursor.moveToFirst();//this statement can check if data==null
		long totalSum = 0;
		do {
			totalSum += cursor.getLong(cursor.getColumnIndex(DbContract.TABLE_DAILY_WORKOUT.COLUMN_DURATION));
			//Log.d(TAG," "+totalSum);
		} while (cursor.moveToNext());
		//long totalTime = 0;//Long.parseLong(cursor.getInt(0));
		cursor.close();
		return totalSum;
	}
	
	private String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());//capital MM is necessary
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private class GetTotalTimeToday extends AsyncTask<Void, Void, Long> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		
		@Override
		protected Long doInBackground(Void... params) {
			SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
			return getTotalTimeToday(database);
		}
		
		@Override
		protected void onPostExecute(Long result) {
			//Log.d(TAG," "+result);
			todaysTotalTime = result;
			DatabaseManager.getInstance().closeDatabase();
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}
}
