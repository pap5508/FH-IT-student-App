package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.adapter.LoginDataBaseAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	Button log,bk;
	LoginDataBaseAdapter ldba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		ldba = new LoginDataBaseAdapter(this);
		ldba= ldba.open();
		bk = (Button)findViewById(R.id.back);
		log = (Button)findViewById(R.id.login_button);
		//register =(Button)findViewById(R.id.register_button);
		//set on click for register
		bk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		log.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText editTextUserName=(EditText)findViewById(R.id.editTextUserName);
				final  EditText editTextPassword=(EditText)findViewById(R.id.editTextpass);
				String username = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();
				String storedPassword=ldba.getSinlgeEntry(username);
				if(password.equals(storedPassword)){
					Toast.makeText(LoginActivity.this, "Congrats!", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(LoginActivity.this, "UserName or Password incorrect", Toast.LENGTH_LONG).show();
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