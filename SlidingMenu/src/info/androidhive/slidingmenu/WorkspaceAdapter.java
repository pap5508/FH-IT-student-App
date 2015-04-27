package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.helper.WorkSpace_Helper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorkspaceAdapter extends BaseAdapter {

	public static final String KEY_ID = "_id";
	public static final String KEY_DATE = "date";
	public static final String KEY_SUBJECT = "subject";
	public static final String KEY_NOTE = "note";

	private static final String DATABASE_USER = "FH_Kiel_user";

	private Context context;
	private SQLiteDatabase sdb;
	private WorkSpace_Helper helper;

	private ArrayList<WorkSpaceRecord> works = new ArrayList<WorkSpaceRecord>();

	/*
	 * public WorkspaceAdapter() {
	 * 
	 * works.add(new WorkSpaceRecord("18-05-2014", "Professor Appointment",
	 * "Room no c12-2.14")); }
	 */

	public WorkspaceAdapter(Context context) {
		this.context = context;
	}

	public WorkspaceAdapter open() throws SQLException {
		helper = new WorkSpace_Helper(context);
		sdb = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		helper.close();
	}

	public long create(String date, String subject, String note) {
		ContentValues cv = new ContentValues();

		cv.put(KEY_DATE, date);
		cv.put(KEY_SUBJECT, subject);
		cv.put(KEY_NOTE, note);

		return sdb.insert(DATABASE_USER, null, cv);

	}

	public void remove(long id) {
		
		
		
		sdb.execSQL("DELETE FROM FH_Kiel_user WHERE _id = " + id);
	}
	
	public void edit(long id) {
		// TODO Auto-generated method stub
		
		
		
	}
	public void share(long id) {
		// TODO Auto-generated method stub
		sdb.execSQL("SELECT note FROM FH_Kiel_user WHERE _id = " + id);
		
		Intent intent= new Intent(context, ContactBadgeActivity.class);
      
      context.startActivity(intent);
  }
		
		
		
	

	public Cursor fetchAllNotes() {

		Cursor mCursor = sdb.query(DATABASE_USER, new String[] { KEY_DATE,
				KEY_SUBJECT, KEY_NOTE }, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/*
	 * public Cursor verifyUserType(String dutype) { Cursor mCursor =
	 * sdb.query(DATABASE_USER, null, KEY_DTYPE + "='" + dutype + "'", null,
	 * null, null, null, null); if (mCursor != null) { mCursor.moveToFirst(); }
	 * return mCursor; }
	 */

	public boolean Login(String usertype, String username, String password)
			throws SQLException {
		Cursor mCursor = sdb.rawQuery("SELECT * FROM " + DATABASE_USER
				+ " WHERE dutype=? AND duname=? AND dpassword=?", new String[] {
				usertype, username, password });
		if (mCursor != null) {
			if (mCursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

	/*
	 * public Cursor queueAll() {
	 * 
	 * String[] columns = new String[] { KEY_DUNAME, KEY_DPASSWORD, KEY_DCITY,
	 * KEY_DSTATE, KEY_DZIP, KEY_DPH };
	 * 
	 * Cursor cursor = sdb.query(DATABASE_USER, columns, null, null, null, null,
	 * null);
	 * 
	 * if (cursor != null) { cursor.moveToFirst(); }
	 * 
	 * return cursor;
	 * 
	 * }
	 */

	public void addWorkRecord(WorkSpaceRecord record) {
		works.add(record);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return works.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater
					.inflate(R.layout.main_work_list_item, parent, false);
		}
		WorkSpaceRecord work = works.get(index);
		TextView dateview = (TextView) view.findViewById(R.id.date_view_pap1);
		dateview.setText(work.getDate());
		TextView subjectview = (TextView) view
				.findViewById(R.id.subject_view_pap1);
		subjectview.setText(work.getSubject());

		TextView notesview = (TextView) view.findViewById(R.id.notes_view_pap1);
		notesview.setText(work.getNotes());

		return view;
	}

	public Cursor fetchAllCountries() {
		// TODO Auto-generated method stub
		Cursor mCursor = sdb
				.query(DATABASE_USER, new String[] { KEY_ID, KEY_DATE,
						KEY_SUBJECT, KEY_NOTE }, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor getNote(long id) throws SQLException {
		
		Cursor c = sdb.rawQuery("SELECT note FROM FH_Kiel_user WHERE _id = " + id, null);
		return c;
		
		
	}
	/*public void notes(int position) {
		// TODO Auto-generated method stub
		WorkSpaceRecord item = works.get(position);
		String message = item.getNotes().toString();
		//Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		//Intent i = new Intent(InfoSystemActivity.this, InfoContactActivity.class);
	      // startActivity(i);
		
		Intent i = new Intent(context, ContactBadgeActivity.class);
	    i.putExtra("mes", message);
	    context.startActivity(i);
	}*/
	
}
