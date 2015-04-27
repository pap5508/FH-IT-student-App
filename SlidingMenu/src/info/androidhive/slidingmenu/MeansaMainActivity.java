package info.androidhive.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MeansaMainActivity extends Activity {
	
	//Intitialising the url

	private String finalUrl = "http://rss.imensa.de/kiel/schwentine-mensa/speiseplan.rss";
	private HandleXml obj;
	private TextView title, description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mensa_activity_main);
		
		//from the xml
		title = (TextView) findViewById(R.id.day);
		// link = (EditText)findViewById(R.id.editText2);
		description = (TextView) findViewById(R.id.description);
		
//call the parser by passing the url to its instance
		obj = new HandleXml(finalUrl);
		obj.fetchXML();
		while (obj.parsingComplete)
			;
		//setter methods
		title.setText(obj.getTitle());
		// link.setText(obj.getLink());
		description.setText(obj.getDescription());
	}

	// public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

}
