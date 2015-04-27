package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Workspce_MainActivity extends Activity {
	Button allnote, addnote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workspace_main);

		allnote = (Button) findViewById(R.id.allnote1);
		addnote = (Button) findViewById(R.id.addnote1);

		allnote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent allnotes = new Intent(getApplicationContext(),
						WorkDisplayActivity.class);
				startActivity(allnotes);
			}
		});

		addnote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent addnotes = new Intent(getApplicationContext(),
						AddWorkSpaceActivity.class);
				startActivity(addnotes);
			}
		});
	}

}
