package info.androidhive.slidingmenu;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddWorkSpaceActivity extends Activity {

	protected EditText dateview, subjectview, notesview;
	protected Button save, cancel, datepick;
	WorkspaceAdapter adapter;
	Cursor cursor;
	static final int DATE_DIALOG_ID = 1;

	protected String date, subject, note;
	SimpleCursorAdapter dataAdapter;
	private int year, month, day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_workspace_entry);

		dateview = (EditText) findViewById(R.id.datum_entry_pap);

		subjectview = (EditText) findViewById(R.id.subject_entry_pap);
		notesview = (EditText) findViewById(R.id.notes_entry_pap);
		save = (Button) findViewById(R.id.save_pap);
		adapter = new WorkspaceAdapter(getApplicationContext());

		dateview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}
		});
		final Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);

		updateDate();

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				date = dateview.getText().toString();
				subject = subjectview.getText().toString();
				note = notesview.getText().toString();

				System.out.println(date);
				System.out.println(subject);
				System.out.println(note);
				adapter.open();

				adapter.create(date, subject, note);

				dateview.setText("");
				subjectview.setText("");
				notesview.setText("");

				Intent get = new Intent(getApplicationContext(),
						Workspce_MainActivity.class);
				startActivity(get);

				adapter.close();

				finish();
				// Toast.makeText(getApplicationContext(),
				// "Inserted Successfully", Toast.LENGTH_SHORT).show();
				// dateview.setText("");
				// subjectview.setText("");
				// notesview.setText("");

			}

			/*
			 * private void displayListView() { // TODO Auto-generated method
			 * stub
			 * 
			 * Cursor cursor = adapter.fetchAllNotes(); String[] columns = new
			 * String[] { WorkspaceAdapter.KEY_ID, WorkspaceAdapter.KEY_DATE,
			 * WorkspaceAdapter.KEY_SUBJECT, WorkspaceAdapter.KEY_NOTE
			 * 
			 * };
			 * 
			 * }
			 */
		});

		cancel = (Button) findViewById(R.id.cancel_pap);

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});

	}

	private void updateDate() {
		// TODO Auto-generated method stub
		dateview.setText(new StringBuilder().append(day).append('-')
				.append(month + 1).append('-').append(year));
	}

	private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int yr, int monthOfYear,
				int dayOfMonth) {

			year = yr;
			month = monthOfYear;
			day = dayOfMonth;
			updateDate();
		}
	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, dateListener, year, month, day);

		}
		return null;

	}

}
