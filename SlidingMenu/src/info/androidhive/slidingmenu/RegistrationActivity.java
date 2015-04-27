package info.androidhive.slidingmenu;

import java.util.ArrayList;

import info.androidhive.slidingmenu.adapter.LoginDataBaseAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;
 
public class RegistrationActivity extends Activity
{
    EditText editTextUserName,editTextPassword,editTextMatno,editTextEmail,editTextConfirmPassword;
    Button btnCreateAccount,bk;
    Spinner spinner,sem;
    ArrayList<String> semarray = new ArrayList<String>();
    MultiAutoCompleteTextView macv;
    
 
    LoginDataBaseAdapter ldba;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        bk =(Button)findViewById(R.id.back);
        bk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
        //Sem array spinner
        spinner =(Spinner)findViewById(R.id.spinnerSem);
        semarray.add("SS 2015");
        semarray.add("WS 2014/2015");
        semarray.add("SS 2014");
        semarray.add("WS 2013/2014");
        semarray.add("SS 2013");
        semarray.add("WS 2012/2013");
        
        //adapter for spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RegistrationActivity.this
                ,android.R.layout.simple_spinner_item,semarray);
      
                     // Set the Adapter
      spinner.setAdapter(arrayAdapter);
      
      // Set the ClickListener for Spinner
      spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() { 

                  public void onItemSelected(AdapterView<?> adapterView, 
                 View view, int i, long l) { 
                 // TODO Auto-generated method stub
             
                   }
                    // If no option selected
        public void onNothingSelected(AdapterView<?> arg0) {
         // TODO Auto-generated method stub
              
        } 

            });
      //Mutli auto complete for courses
      String[] courses= getResources().getStringArray(R.array.list_of_courses_WS2014);
      ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,courses);
      macv = (MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView1);
      macv.setAdapter(adapter);
      macv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        // get Instance  of Database Adapter
        ldba=new LoginDataBaseAdapter(this);
        ldba=ldba.open();
 
        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUser);
        editTextPassword=(EditText)findViewById(R.id.EditTextpass);
        editTextConfirmPassword = (EditText)findViewById(R.id.editTextCofirm);
        editTextMatno=(EditText)findViewById(R.id.editText);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        //multicourse=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView1);
        sem= (Spinner)findViewById(R.id.spinnerSem);
 
        btnCreateAccount=(Button)findViewById(R.id.register_button);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
 
        public void onClick(View v) {
            // TODO Auto-generated method stub
 
            String userName=editTextUserName.getText().toString();
            String password=editTextPassword.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();
            String matno=editTextMatno.getText().toString();
            String email=editTextEmail.getText().toString();
            String semester = sem.getSelectedItem().toString();
            String courses = macv.getText().toString();
 
            // check if any of the fields are vaccant
            if(userName.equals("")||password.equals("")||confirmPassword.equals("")||matno.equals("")||email.equals(""))
            {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
            }
            // check if both password matches
            if(!password.equals(confirmPassword))
            {
                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                // Save the Data in Database
                ldba.insertEntry(userName, password,matno,email,semester,courses);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            }
        }
    });
}
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
 
        ldba.close();
    }
}