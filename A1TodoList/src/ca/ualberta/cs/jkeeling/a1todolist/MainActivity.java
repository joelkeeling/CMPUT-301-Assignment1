package ca.ualberta.cs.jkeeling.a1todolist;
import java.util.ArrayList;
import java.util.List;



import ca.ualberta.cs.jkeeling.a1todolist.adapters.ItemAdapter;
import ca.ualberta.cs.jkeeling.a1todolist.data.FileDataManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupMenu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private List<TDItem> activeItemsList = new ArrayList<TDItem>();
	private List<TDItem> allItemsList = new ArrayList<TDItem>();
	private EditText textBox; 
	private ItemAdapter adapter; 
	private Button optionsBtn;
	private FileDataManager fdm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textBox = (EditText) findViewById(R.id.AddTextBox);
		optionsBtn = (Button) findViewById(R.id.OptionsButton);
		fdm = new FileDataManager(this.getApplicationContext());		
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
		allItemsList = fdm.loadItems();		
		for (TDItem item : allItemsList){
			if (item.getArchiveState() == false){
				activeItemsList.add(item);
			}
		}
		//Set adapter
		adapter = new ItemAdapter(this, R.layout.item_row, activeItemsList);		
		ListView listView = (ListView) findViewById(R.id.TDList);
		listView.setAdapter(adapter);
				
	}	
		
	public void Add(View v){
		String text = textBox.getText().toString();
		TDItem item = new TDItem(text);
		activeItemsList.add(item);
		allItemsList.add(item);
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
		textBox.setText("");
		
	}
	
	public void OptionsMenu(View v){
		PopupMenu popup = new PopupMenu(this, optionsBtn);
		popup.getMenuInflater().inflate(R.menu.main_options_popup, popup.getMenu());
		popup.show();
	}
	
	public void toSummary(MenuItem i){
		
	}
	
	public void toArchive(MenuItem i){
		unSelectAll();
		Intent intent = new Intent(this, ArchiveActivity.class);		
		startActivity(intent);
	}
	
	public void unSelectAll(){
		for (TDItem item : allItemsList){
			item.setSelected(false);
		}
		fdm.saveItems(allItemsList);
	}
	
	public List<TDItem> getSelected(){
		List<TDItem> selectedItemsList = new ArrayList<TDItem>();
		for (TDItem item : activeItemsList){
			if (item.getSelected() == true){
				selectedItemsList.add(item);
			}
		}
		return selectedItemsList;
	}
	
	public void archiveAll(MenuItem i){
		for (TDItem item : activeItemsList){
			item.setSelected(true);
		}
		archiveSelected(i);
	}
	
	public void archiveSelected(MenuItem i){
		List<TDItem> itemList = getSelected();
		for (TDItem item : itemList){
			item.archive();
			activeItemsList.remove(item);
		}
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
	}
	
	public void toSelector(MenuItem i){
		
	}	
	
	public void emailAll(MenuItem i){
		
	}
	
	public void Delete(View v){
		TDItem target = getTargetItem(v);
		activeItemsList.remove(target);
		allItemsList.remove(target);
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
	}
	
	public void check(View v){
		TDItem target = getTargetItem(v);
		CheckBox checkbox = (CheckBox)v;
		checkbox.setChecked(target.toggleChecked());
		fdm.saveItems(allItemsList);
	}
	
	public void select(View v){
		TDItem target = getTargetItem(v);
		if (target.toggleSelected() == true){
			((View)v.getParent()).setBackgroundColor(Color.rgb(255, 153, 0));
		}
		else {
			((View)v.getParent()).setBackgroundColor(Color.WHITE);
		}
		fdm.saveItems(allItemsList);
	}
	
	public TDItem getTargetItem(View v){
		View parent = (View)v.getParent();			
		CustomTextView text = (CustomTextView) parent.findViewById(R.id.textView);
		String uId = (String) text.getUId();
		TDItem target = null;		
		for (TDItem item : activeItemsList){
			if (item.getId().equals(uId)){
				target = item;
			}
		}
		return target;
	}
}
