package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements OnClickListener {
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View loginView = inflater.inflate(R.layout.fragment_home, container, false);
        
        Button log = (Button)loginView.findViewById(R.id.login);
        Button reg =(Button)loginView.findViewById(R.id.registration);
        log.setOnClickListener(this);
         reg.setOnClickListener(this);
        return loginView;
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			Intent i = new Intent(getActivity().getApplicationContext(),LoginActivity.class);
			startActivity(i);
			
			break;
			case R.id.registration:
				Intent i1 = new Intent(getActivity().getApplicationContext(),RegistrationActivity.class);
				startActivity(i1);
				
				break;

		default:
			break;
		}
		
		
	}
}
