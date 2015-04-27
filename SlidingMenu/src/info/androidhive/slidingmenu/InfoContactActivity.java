package info.androidhive.slidingmenu;

import android.app.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import info.androidhive.slidingmenu.InfoSystemActivity;

public class InfoContactActivity extends Activity {
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	InfoSystemActivity isa;
	String data;
 
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
		
		//to retrieve contatct groups
		ImageView img1 = (ImageView) findViewById(R.id.contactgroup_logo);
		img1.setOnClickListener(new OnClickListener() {
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
	
	//seperate method to get the values of items
	
	
	
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
