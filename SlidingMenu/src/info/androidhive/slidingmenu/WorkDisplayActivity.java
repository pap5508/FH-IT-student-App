package info.androidhive.slidingmenu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class WorkDisplayActivity extends Activity {

	Cursor cursor;
	ListView worklist;
	WorkspaceAdapter workspace_adapter;
	SimpleCursorAdapter dataAdapter;
	private static final int TIME_ENTRY_REQUEST_CODE = 1;
	ContactBadgeActivity cba;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_main);

		worklist = (ListView) findViewById(R.id.work_list);
		workspace_adapter = new WorkspaceAdapter(this);
		worklist.setAdapter(workspace_adapter);
		registerForContextMenu(worklist);
		workspace_adapter.open();
		workspace_adapter.fetchAllCountries();

		Displayview();

	}

	public void Displayview() {
		// TODO Auto-generated method stub
		cursor = workspace_adapter.fetchAllCountries();
		String[] columns = new String[] { WorkspaceAdapter.KEY_ID,
				WorkspaceAdapter.KEY_DATE, WorkspaceAdapter.KEY_SUBJECT,
				WorkspaceAdapter.KEY_NOTE, };
		int[] to = new int[] { R.id.date_view_pap1, R.id.subject_view_pap1,
				R.id.notes_view_pap1, };

		dataAdapter = new SimpleCursorAdapter(this,
				R.layout.main_work_list_item, cursor, columns, to, 0);

		ListView listView = (ListView) findViewById(R.id.work_list);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				//String text = cursor.toString();

				// Get the state's capital from this row in the database.
				String countryCode = cursor.getString(cursor
						.getColumnIndexOrThrow("code"));
				Toast.makeText(getApplicationContext(), countryCode,
						Toast.LENGTH_SHORT).show();

			}
		});
	}
 
	/*public void CursortoString(){
		
		String text = cursor.toString();
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_work_list_menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add_menu_list) {
			Intent intent = new Intent(this, AddWorkSpaceActivity.class);
			startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		// super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_work_context_menu, menu);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.delete_item:
			Toast.makeText(this, "Deleted.", Toast.LENGTH_SHORT).show();
            //int selected = worklist.getSelectedItemPosition();
			
			//Track track = (Track)mAdapter.getItem(info.position);
            
			workspace_adapter.remove(info.id);
			workspace_adapter.fetchAllCountries();
			Displayview();
			return true;
		case R.id.edit_item:
			//Toast.makeText(this, "Share Called.", Toast.LENGTH_SHORT).show();
			
			
			workspace_adapter.edit(info.id);
			workspace_adapter.fetchAllCountries();
			Displayview();
			return true;
			
		case R.id.share_item:
			//Toast.makeText(this, "Share Called.", Toast.LENGTH_SHORT).show();
			//workspace_adapter.notes(info.position);
			//cba.sendSMS(info.id);
			Cursor cr = workspace_adapter.getNote(info.id);
			
			if(cr != null){
				if(cr.moveToFirst()){
					String notes = cr.getString(cr.getColumnIndex("note"));
					Intent i = new Intent(WorkDisplayActivity.this, ContactBadgeActivity.class);
				    i.putExtra("mes", notes);
				   startActivity(i);
				}
			}
			cr.close();
			
			
			
			//workspace_adapter.fetchAllCountries();
			//Displayview();
			return true;


		default:
			return super.onContextItemSelected(item);
		}
	}

	
	
	
	// onActivityResult code

	/*
	 * protected void onActivityResult(int requestCode, int resultCode, Intent
	 * data) { if (requestCode == TIME_ENTRY_REQUEST_CODE) { if (resultCode ==
	 * RESULT_OK) { String date = data.getStringExtra("date"); String subject =
	 * data.getStringExtra("subject"); String notes =
	 * data.getStringExtra("notes");
	 * 
	 * workspace_adapter.addWorkRecord(new WorkSpaceRecord(date, subject,
	 * notes)); workspace_adapter.notifyDataSetChanged();
	 * workspace_adapter.fetchAllCountries(); Displayview(); } } }
	 */

}
