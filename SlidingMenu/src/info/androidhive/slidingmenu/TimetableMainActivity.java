package info.androidhive.slidingmenu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TimetableMainActivity extends Activity {

	Button btn;
	File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_main);
		btn = (Button) findViewById(R.id.button_timetable);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				 CopyReadAssets();
				// file = new File(
				// "content://com.android.Samplefortimetable/timetable/Master_IT.pdf");
				// if (file.exists()) {
				// Uri path = Uri.fromFile(file);
				// Intent intent = new Intent(Intent.ACTION_VIEW);
				// intent.setDataAndType(path, "application/pdf");
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				// startActivity(intent);

				// } else {
				startDownload();
				// }

			}

			private void CopyReadAssets() {
				AssetManager assetManager = getAssets();

				InputStream in = null;
				OutputStream out = null;
				File file = new File(getFilesDir(), "Master_IT.pdf");
				try {
					in = assetManager.open("Master_IT.pdf");
					out = openFileOutput(file.getName(),
							Context.MODE_WORLD_READABLE);

					copyFile(in, out);
					in.close();
					in = null;
					out.flush();
					out.close();
					out = null;
				} catch (Exception e) {
					Log.e("tag", e.getMessage());
				}

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(
						Uri.parse("file://" + getFilesDir() + "/Master_IT.pdf"),
						"application/pdf");

				startActivity(intent);

			}

			private void copyFile(InputStream in, OutputStream out)
					throws IOException {
				byte[] buffer = new byte[1024];
				int read;
				while ((read = in.read(buffer)) != -1) {
					out.write(buffer, 0, read);
				}

			}

			private void startDownload() {
				// TODO Auto-generated method stub
				String url = "http://www.fh-kiel.de/fileadmin/data/IuE/studium/stundenplan/Master_IT.pdf";
				new DownloadFileAsync().execute(url);
			}

			private void openPDF() {
				// TODO Auto-enerated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/PDF";

				intent.setDataAndType(Uri.fromFile(file), "application/pdf");
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
