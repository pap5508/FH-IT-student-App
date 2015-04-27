package info.androidhive.slidingmenu.adapter;

import info.androidhive.slidingmenu.WorkRecord;

import java.util.ArrayList;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorkspaceAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<WorkRecord> works =new ArrayList<WorkRecord>();
	public WorkspaceAdapter(Context context, ArrayList<WorkRecord> works){
		this.context=context;
		this.works=works;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return works.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view==null){
			 LayoutInflater inflater = (LayoutInflater)
	                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
		}
		return view;
		
	}

}
