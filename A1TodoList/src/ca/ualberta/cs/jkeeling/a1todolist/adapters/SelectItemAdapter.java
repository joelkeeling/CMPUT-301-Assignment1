package ca.ualberta.cs.jkeeling.a1todolist.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import ca.ualberta.cs.jkeeling.a1todolist.CustomTextView;
import ca.ualberta.cs.jkeeling.a1todolist.R;
import ca.ualberta.cs.jkeeling.a1todolist.TDItem;
import ca.ualberta.cs.jkeeling.a1todolist.R.id;
import ca.ualberta.cs.jkeeling.a1todolist.R.layout;

public class SelectItemAdapter extends ArrayAdapter<TDItem> {
	public SelectItemAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}

	private List<TDItem> itemList;

}
