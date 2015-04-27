package info.androidhive.slidingmenu.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WorkSpace_Helper extends SQLiteOpenHelper

{
	public static String DATABASE_NAME = "FH-Kiel";

	public WorkSpace_Helper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, 3);

	}

	public WorkSpace_Helper(Context context) {
		super(context, DATABASE_NAME, null, 1);

	}

	private static final String DATABASE_CREATE = "create table FH_Kiel_user (_id integer primary key autoincrement,date text, "
			+ "subject text , note text);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Helper", "Creating Table");
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
