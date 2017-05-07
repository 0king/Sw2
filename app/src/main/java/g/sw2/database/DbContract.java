package g.sw2.database;

import android.provider.BaseColumns;

/**
 * Created by 5dr on 23/04/17.
 */


//1. contract/schema - table structure - row, col
//2. DatabaseHelper extends SQLiteOpenHelper - create db, upgrade
// CRUD ops
//3. database manager - concurrency
//do ops in AsyncTask
//Acess ways - 1. POJO model 2. Directly 3. cursor loader
// 4, content provider (not using it now)
//4.ContentProvider - or If you don't need this, you can just override method loadInBackgroud() of the CursorLoader class


public final class DbContract {
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "saraswati.db";
	private static final String TEXT_TYPE = " TEXT";//String
	private static final String INT_TYPE = " INTEGER";//long
	private static final String REAL_TYPE = " REAL";//double
	private static final String DATE_TYPE = " DATETIME";
	private static final String COMMA_SEP = ",";
	private static final String NOT_NULL = " NOT NULL";
	
	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	private DbContract() {
	}
	
	/* we need to store Date, time duration, history (studied - yes, no),  card_id_start, card_id_end */
	public static abstract class TABLE_DAILY_WORKOUT implements BaseColumns {
		public static final String TABLE_NAME = "daily_journey_details";//table name
		/* table coloumns: */
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_DATETIME = "date_time";
		public static final String COLUMN_DATE = "date";
		public static final String COLUMN_DURATION = "time_duration";
		public static final String COLUMN_START_JOURNEY = "start_journey";
		public static final String COLUMN_END_JOURNEY = "end_journey";
		public static final String COLUMN_HISTORY = "history";
		public static final String COLUMN_CREATED_AT = "created_at";
		
		public static final String HISTORY_DEFAULT_VALUE = " 0";
		
		/* create statement */
		public static final String CREATE_TABLE_JOURNEY = "CREATE TABLE " +
				                                                  TABLE_NAME +
				                                                  " (" +
				                                                  _ID + " INTEGER PRIMARY KEY," +
				                                                  COLUMN_CREATED_AT + DATE_TYPE + " DEFAULT CURRENT_TIMESTAMP" + COMMA_SEP +
				                                                  COLUMN_DATETIME + TEXT_TYPE + COMMA_SEP +
				                                                  COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
				                                                  COLUMN_DURATION + INT_TYPE + NOT_NULL + COMMA_SEP +
				                                                  COLUMN_HISTORY + INT_TYPE + " DEFAULT" + HISTORY_DEFAULT_VALUE +
				                                                  " )";
		
		public static final String DELETE_TABLE_JOURNEY = "DROP TABLE IF EXISTS " + TABLE_NAME;
	}
	
	
}

	/*
	CURRENT_TIME – Inserts only time
	CURRENT_DATE – Inserts only date
	CURRENT_TIMESTAMP – Inserts both time and date
	*/