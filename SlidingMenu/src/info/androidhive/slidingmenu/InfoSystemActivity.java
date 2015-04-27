package info.androidhive.slidingmenu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoSystemActivity extends Activity implements OnItemClickListener {

	InfoContactActivity ic;
	
	private class RowItem {
		String title = null;
		String description = null;
		String url = null;
		String author = null;
		String pubDate = null;

		public RowItem(String title, String description, String url,
				String author, String pubDate) {
			this.title = title;
			this.description = description;
			this.url = url;
			this.author = author;
			this.pubDate = pubDate;
		}
	}

	private class DownloadXMLTask extends AsyncTask<String, Void, String> {

		ArrayList<RowItem> items = null;

		public DownloadXMLTask(ArrayList<RowItem> items) {
			this.items = items;
		}

		// local helper method
		private String getNodeValue(Element entry, String tag) {
			Element tagElement = (Element) entry.getElementsByTagName(tag)
					.item(0);
			return tagElement.getFirstChild().getNodeValue();
		}

		private String getValue(Element entry, String tag) {
			Element tagElement = (Element) entry.getElementsByTagName(tag)
					.item(0);
			return tagElement.getFirstChild().getNodeValue()
					.replaceAll("\\<.*?\\>", " ").trim();

		}

		private String downloadAndParseXML(String url) {
			try {
				InputStream in = new java.net.URL(url).openStream();

				// build the XML parser
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				// parse and get XML elements
				Document dom = db.parse(in);
				Element documentElement = dom.getDocumentElement();

				// we want all XML children called 'item'
				NodeList nodes = documentElement.getElementsByTagName("item");

				// for each 'item' do the following
				for (int i = 0; i < nodes.getLength(); i++) {
					Element entry = (Element) nodes.item(i);
					String author = getNodeValue(entry, "author");

					String[] includeauthorarray = { "robert.manzke@fh-kiel.de",
							"carsten.meyer@fh-kiel.de", "nils.gruschka@fh-kiel.de", "hauke.schramm@fh-kiel.de" };

					if (Arrays.asList(includeauthorarray).contains(author)) {
						// get the nodes from 'item' that you need
						String title = getNodeValue(entry, "title");
						String description = getValue(entry, "content:encoded");
						String link = getNodeValue(entry, "link");

						String pubdate = getNodeValue(entry, "pubDate");
						// add them to your list
						items.add(new RowItem(title, description, link, author,
								pubdate));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected String doInBackground(String... urls) {
			if (urls.length <= 0)
				return null;
			return downloadAndParseXML(urls[0]);
		}

		protected void onPostExecute(String result) {
			updateList(items);
		}
	}

	public void updateList(ArrayList<RowItem> items) {
		CustomArrayAdapter adapter = new CustomArrayAdapter(this,
				R.layout.infosystem_list_item, items);
		listView.setAdapter(adapter);
	}

	// class used to have custom view for the list item
	private class CustomArrayAdapter extends ArrayAdapter<RowItem> {
		Context context;
		List<RowItem> items;

		private class ViewHolder {
			public TextView title;
			public TextView description;
			public TextView author;
			public TextView pubdate;
		}

		public CustomArrayAdapter(Context context, int resource,
				List<RowItem> items) {
			super(context, resource, items);
			this.context = context;
			this.items = items;
		}

		@Override
		public View getView(int position, View rowView, ViewGroup parent) {
			// reuse the rowView if possible - for efficiency and less memory
			// consumption
			if (rowView == null) {

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				rowView = inflater.inflate(R.layout.infosystem_list_item, null);
				// configure view holder
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.title = (TextView) rowView.findViewById(R.id.title);
				viewHolder.description = (TextView) rowView
						.findViewById(R.id.description);
				viewHolder.author = (TextView) rowView
						.findViewById(R.id.author);
				viewHolder.pubdate = (TextView) rowView
						.findViewById(R.id.pubdate);
				rowView.setTag(viewHolder);
			}
			// set the view
			ViewHolder holder = (ViewHolder) rowView.getTag();
			RowItem item = items.get(position);
			holder.title.setText(item.title);
			holder.description.setText(item.description);
			holder.author.setText(item.author);
			holder.pubdate.setText(item.pubDate);

			return rowView;
		}
	}

	ListView listView;
	ArrayList<RowItem> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infosystem_list_activity_main);

		// get the listView and set this to be the onItemClickListener
		listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);
		registerForContextMenu(listView);

		// create the RowItem list - used for storing one news feed
		items = new ArrayList<RowItem>();

		// start the background task to get the news feed
		new DownloadXMLTask(items)
				.execute("http://www.fh-kiel.de/index.php?id=4317&type=100");
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		// super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_info_list_menu, menu);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		
		switch (item.getItemId()) {
		case R.id.share_info:
			//Toast.makeText(this, "Share Called.", Toast.LENGTH_SHORT).show();
			message(info.position);
			
			
		       
			return true;	
		}
		
	
		return super.onContextItemSelected(item);	
		
	}
	
	
	private void message(int position) {
		// TODO Auto-generated method stub
		
		RowItem item = items.get(position);
		String message = item.description.toString();
		//Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		//Intent i = new Intent(InfoSystemActivity.this, InfoContactActivity.class);
	      // startActivity(i);
		Intent i = new Intent(InfoSystemActivity.this, InfoContactActivity.class);
	    i.putExtra("mes", message);
	    startActivity(i);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long id) {
		RowItem item = items.get(position);
		
		
		// start browser with the url
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse(item.url));
		startActivity(browserIntent);
	}
	
	
	
}