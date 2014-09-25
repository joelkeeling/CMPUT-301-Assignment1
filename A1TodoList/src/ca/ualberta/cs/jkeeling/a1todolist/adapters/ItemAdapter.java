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

	

public class ItemAdapter extends ArrayAdapter<TDItem> {
	private List<TDItem> itemList;
	private Context context;
	public ItemAdapter(Context context, int resource, List<TDItem> itemList) {
		super(context, resource, itemList);
		this.context = context;
		this.itemList = itemList;
		// TODO Auto-generated constructor stub
	}
	private class ViewHolder {
	   CustomTextView name;
	   CheckBox box;
	  }
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		//Used createViewFromResource + blog
		ViewHolder holder = null;
		
		if (convertView == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.item_row, null);
			holder = new ViewHolder();						
			holder.name = (CustomTextView) convertView.findViewById(R.id.textView);
		    holder.box = (CheckBox) convertView.findViewById(R.id.checkBox);
			convertView.setTag(holder);
				 
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}			
		TDItem item = itemList.get(position);
		holder.name.setText(item.getName());
		holder.name.setUId(item.getId());
		holder.box.setChecked(item.getChecked());
		
		return convertView;
	}		
}