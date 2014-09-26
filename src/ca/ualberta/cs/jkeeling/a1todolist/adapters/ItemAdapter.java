package ca.ualberta.cs.jkeeling.a1todolist.adapters;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import ca.ualberta.cs.jkeeling.a1todolist.R;
import ca.ualberta.cs.jkeeling.a1todolist.models.CustomTextView;
import ca.ualberta.cs.jkeeling.a1todolist.models.TDItem;	

// Custom adapter class to enable custom rows in a ListView
// Each custom rows includes a checkbox, textview, and button
public class ItemAdapter extends ArrayAdapter<TDItem> {
	private List<TDItem> itemList;
	private Context context;
	private int resource;
	
	private class ViewHolder {
		   CustomTextView name;
		   CheckBox box;
		  }
	
	public ItemAdapter(Context context, int resource, List<TDItem> itemList) {
		super(context, resource, itemList);
		this.context = context;
		this.itemList = itemList;
		this.resource = resource;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		//Used createViewFromResource + blog
		ViewHolder holder = null;
		
		if (convertView == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(this.resource, null);
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
		if (item.getSelected() == true){
			convertView.setBackgroundColor(Color.rgb(255, 153, 0));
		}
		else {
			convertView.setBackgroundColor(Color.WHITE);
		}
		return convertView;
	}		
}