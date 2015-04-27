package info.androidhive.slidingmenu;

import java.io.BufferedInputStream;
import java.io.InputStream;

import info.androidhive.slidingmenu.WorkDisplayActivity;
import info.androidhive.slidingmenu.helper.WorkSpace_Helper;
import android.net.Uri;
import android.os.Bundle; 
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter.CursorToStringConverter;
import android.telephony.gsm.SmsManager;

public class ContactBadgeActivity extends Activity {
	
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	private static final int PICK_CONTACT = 3;
    private String contactID;  
    WorkspaceAdapter workspace_adapter;
    String data;

	public static final String KEY_ID = "_id";
	public static final String KEY_DATE = "date";
	public static final String KEY_SUBJECT = "subject";
	public static final String KEY_NOTE = "note";

	private static final String DATABASE_USER = "FH_Kiel_user";

	private Context context;
	private SQLiteDatabase sdb;
	private WorkSpace_Helper helper;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_contact_design);
        buttonSend = (Button) findViewById(R.id.buttonSend);
		textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		textSMS = (EditText) findViewById(R.id.editTextSMS);
		
		//textSMS.setText(isa.message);
		
		//recieve message
		Intent intent = getIntent();
		 Bundle extras = intent.getExtras();
		    if(extras != null)
		    data = extras.getString("mes");
		    textSMS.setText(data);
		
		
		//to retrieve the contacts
		ImageView img = (ImageView) findViewById(R.id.contact_logo);
		img.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	 Intent q = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
	                startActivityForResult(q, 1001); 
		    }
		});
        
        buttonSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phoneNo = textPhoneNo.getText().toString();
				 
				
				 
				  String sms = textSMS.getText().toString();
	 
				  try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
								Toast.LENGTH_LONG).show();
				  } catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				  }
	 
				}
			});
				
			}
		
        
        

	
	public void onActivityResult(int reqCode, int resultCode, Intent data) {  
        super.onActivityResult(reqCode, resultCode, data);  

        if (resultCode == Activity.RESULT_OK) {  
            // getting the URI from result for further working
            Uri contactData = data.getData();
            Cursor c =  managedQuery(contactData, null, null, null, null);

            if (c.moveToFirst()) {


              String  id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

              String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));



              if (hasPhone.equalsIgnoreCase("1")) {
              Cursor phones = getContentResolver().query( 
                              ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
                              ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
                              null, null);
                    phones.moveToFirst();
                      //this string will hold the contact number
                      String cNumber = phones.getString(phones.getColumnIndex("data1"));
                      textPhoneNo.setText(cNumber);
                      //this string will hold the contact name
                      String cName = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                  }

            }} 
        }
	
}
