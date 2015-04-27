package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FHLinksFragment extends Fragment implements OnClickListener {
	
public FHLinksFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View fhlinksview = inflater.inflate(R.layout.fragment_fhlinks, container, false);
         
        
        
        TextView txt = (TextView)fhlinksview.findViewById(R.id.qis);
        TextView txt1 = (TextView)fhlinksview.findViewById(R.id.modulehandbook);
        TextView txt2 = (TextView)fhlinksview.findViewById(R.id.moduleanmeldung);
        TextView txt3 = (TextView)fhlinksview.findViewById(R.id.idw);
        TextView txt4 = (TextView)fhlinksview.findViewById(R.id.Insurance);
        TextView txt5 = (TextView)fhlinksview.findViewById(R.id.internationaloffice);
        TextView txt6 = (TextView)fhlinksview.findViewById(R.id.Library);
        TextView txt7 = (TextView)fhlinksview.findViewById(R.id.softwaredownload);
        TextView txt8 = (TextView)fhlinksview.findViewById(R.id.sports);
        TextView txt9 = (TextView)fhlinksview.findViewById(R.id.studentenwerk);
       TextView txt10 = (TextView)fhlinksview.findViewById(R.id.email);
       TextView txt11 = (TextView)fhlinksview.findViewById(R.id.lms);
        txt.setOnClickListener(this);
        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);
        txt4.setOnClickListener(this);
        txt5.setOnClickListener(this);
        txt6.setOnClickListener(this);
        txt7.setOnClickListener(this);
        txt8.setOnClickListener(this);
        txt9.setOnClickListener(this);
        txt10.setOnClickListener(this);
        txt11.setOnClickListener(this);
        return fhlinksview;
        
       
        
        
		
    }
	
	public void browser_qis() {
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.qis:
			Uri uri = Uri.parse("https://qis.fh-kiel.de/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case R.id.modulehandbook:
			Uri uri1 = Uri.parse("https://module.fh-kiel.de/");
			Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
			startActivity(intent1);
			break;
		case R.id.moduleanmeldung:
			Uri uri2 = Uri.parse("https://modulanmeldung.fh-kiel.de/");
			Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
			startActivity(intent2);
			break;
		case R.id.idw:
			Uri uri3 = Uri.parse("https://ida.fh-kiel.de/idw/");
			Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
			startActivity(intent3);
			break;
		case R.id.Insurance:
				Uri uri4 = Uri.parse("www.asta.fh-kiel.de/");
				Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
				startActivity(intent4);
				break;
		case R.id.internationaloffice:
			Uri uri5 = Uri.parse("http://www.fh-kiel.de/index.php?id=110");
			Intent intent5 = new Intent(Intent.ACTION_VIEW, uri5);
			startActivity(intent5);
			break;
		case R.id.Library:
			Uri uri6 = Uri.parse("http://www.fh-kiel.de/index.php?id=118");
			Intent intent6 = new Intent(Intent.ACTION_VIEW, uri6);
			startActivity(intent6);
			break;
		case R.id.softwaredownload:
				Uri uri7 = Uri.parse("http://e5.onthehub.com/WebStore/Welcome.aspx?vsro=8&ws=3817c804-8b6f-e011-971f-0030487d8897");
				Intent intent7 = new Intent(Intent.ACTION_VIEW, uri7);
				startActivity(intent7);
				break;
		case R.id.sports:
			Uri uri8 = Uri.parse("http://www.sportzentrum.uni-kiel.de/en");
			Intent intent8 = new Intent(Intent.ACTION_VIEW, uri8);
			startActivity(intent8);
			break;
		case R.id.studentenwerk:
			Uri uri9 = Uri.parse("http://www.studentenwerk-sh.eu/en/home/index.html");
			Intent intent9 = new Intent(Intent.ACTION_VIEW, uri9);
			startActivity(intent9);
			break;
		case R.id.email:
			Uri uri10 = Uri.parse("https://student.fh-kiel.de/webmail/src/webmail.php");
			Intent intent10 = new Intent(Intent.ACTION_VIEW, uri10);
			startActivity(intent10);
			break;
		case R.id.lms:
			Uri uri11 = Uri.parse("https://lms.fh-kiel.de/login/index.php");
			Intent intent11 = new Intent(Intent.ACTION_VIEW, uri11);
			startActivity(intent11);
			break;
				

		default:
			break;
		}
		
	}
}
