package ca.ualberta.cs.jkeeling.a1todolist;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private List<TDItem> allItemsList = new ArrayList<TDItem>();
	private EditText textBox; 
	private ItemAdapter adapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textBox = (EditText) findViewById(R.id.AddTextBox);
		GenerateTDList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void GenerateTDList(){
		//Get Items
		//for testing!
		allItemsList.add(new TDItem("Test"));
		//Set adapter
		adapter = new ItemAdapter(this, R.layout.item_row, allItemsList);
		ListView listView = (ListView) findViewById(R.id.TDList);
		listView.setAdapter(adapter);
				
	}
	
	private class ViewHolder {
		   CustomTextView name;
		   CheckBox box;
		  }	
	
	public class ItemAdapter extends ArrayAdapter<TDItem> {
		private List<TDItem> itemList;
		public ItemAdapter(Context context, int resource, List<TDItem> itemList) {
			super(context, resource, itemList);
			this.itemList = itemList;
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			//Used createViewFromResource + blog
			ViewHolder holder = null;
			
			if (convertView == null){
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			
			return convertView;
		}
		
	}
	
	public void Add(View v){
		String text = textBox.getText().toString();
		TDItem item = new TDItem(text);
		allItemsList.add(item);		
		adapter.notifyDataSetChanged();
		textBox.setText("");			
	}
	public void Delete(View v){
		CustomTextView text = (CustomTextView) v;
		String uId = (String) text.getUId();
		TDItem target = null;
		for (TDItem item : allItemsList){
			if (item.getId().equals(uId)){
				target = item;
			}
		}
		allItemsList.remove(target);
		adapter.notifyDataSetChanged();
	}
}
