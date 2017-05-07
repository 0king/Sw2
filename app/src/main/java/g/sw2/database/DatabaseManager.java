package g.sw2.database;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 5dr on 23/04/17.
 */


/* singleton class Database Manager which will hold and return single SQLiteOpenHelper object.
* DatabaseManager acts like a wrapper around SQLiteOpenHelper
* */
public class DatabaseManager {
	
	//1. contract/schema - table structure - row, col
	//2. DatabaseHelper extends SQLiteOpenHelper - CRUD ops
	//3. database manager - concurrency
	//do ops in AsyncTask
	//Acess ways - 1. POJO model 2. Directly 3. cursor loader 4, content provider
	//4.ContentProvider - or If you don't need this, you can just override method loadInBackgroud() of the CursorLoader class
	
	
	private static DatabaseManager instance;
	private static DbOpenHelper mDatabaseHelper;//SQLiteOpenHelper
	private AtomicInteger mOpenCounter = new AtomicInteger();
	private SQLiteDatabase mDatabase;
	
	
	public static synchronized void initializeInstance(DbOpenHelper helper) {// how to - double locking which reduces overhead ???
		if (instance == null) {
			instance = new DatabaseManager();
			mDatabaseHelper = helper;
		}
	}
	
	public static synchronized DatabaseManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
					                                " is not initialized, call initializeInstance(..) method first.");
		}
		
		return instance;
	}
	
	public synchronized SQLiteDatabase openDatabase() throws SQLException {
		if (mOpenCounter.incrementAndGet() == 1) {
			// Opening new database
			mDatabase = mDatabaseHelper.getWritableDatabase();
		}
		return mDatabase;
	}
	
	public synchronized void closeDatabase() {
		if (mOpenCounter.decrementAndGet() == 0) {
			// Closing database
			mDatabase.close();
			
		}
	}
	
	
}
