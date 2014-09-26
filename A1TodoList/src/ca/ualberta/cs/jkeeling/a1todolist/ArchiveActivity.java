package ca.ualberta.cs.jkeeling.a1todolist;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.jkeeling.a1todolist.adapters.ItemAdapter;
import ca.ualberta.cs.jkeeling.a1todolist.data.FileDataManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

public class ArchiveActivity extends Activity {
	
	private List<TDItem> archivedItemsList = new ArrayList<TDItem>();
	private List<TDItem> allItemsList = new ArrayList<TDItem>();
	private ItemAdapter adapter; 
	private Button optionsBtn;
	private FileDataManager fdm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		optionsBtn = (Button) findViewById(R.id.OptionsButton);	
		fdm = new FileDataManager(this.getApplicationContext());
		GenerateArchiveList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
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
	
	public void GenerateArchiveList(){
		//Get Items
		allItemsList = fdm.loadItems();		
		for (TDItem item : allItemsList){
			if (item.getArchiveState() == true){
				archivedItemsList.add(item);
			}
		}
		//Set adapter
		adapter = new ItemAdapter(this, R.layout.archive_row, archivedItemsList);		
		ListView listView = (ListView) findViewById(R.id.ArchiveList);
		listView.setAdapter(adapter);
				
	}	
	
	public void OptionsMenu(View v){
		PopupMenu popup = new PopupMenu(this, optionsBtn);
		popup.getMenuInflater().inflate(R.menu.archive_options_popup, popup.getMenu());
		popup.show();
	}
	
	
	public void toMain(MenuItem i){
		Intent intent = new Intent(this, MainActivity.class);		
		startActivity(intent);
	}
	
	public void toSummary(MenuItem i){
		
	}
	
	public List<TDItem> getSelected(){
		List<TDItem> selectedItemsList = new ArrayList<TDItem>();
		for (TDItem item : archivedItemsList){
			if (item.getSelected() == true){
				selectedItemsList.add(item);
			}
		}
		return selectedItemsList;
	}
	
	public void unarchiveAll(MenuItem i){
		for (TDItem item : archivedItemsList){
			item.setSelected(true);
		}
		unarchiveSelected(i);
	}
	
	public void unarchiveSelected(MenuItem i){
		List<TDItem> itemList = getSelected();
		for (TDItem item : itemList){
			item.unarchive();
			archivedItemsList.remove(item);
		}
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
	}
	
	public void Delete(View v){
		TDItem target = getTargetItem(v);
		archivedItemsList.remove(target);
		allItemsList.remove(target);
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
	}
	
	public TDItem getTargetItem(View v){
		View parent = (View)v.getParent();			
		CustomTextView text = (CustomTextView) parent.findViewById(R.id.textView);
		String uId = (String) text.getUId();
		TDItem target = null;
		for (TDItem item : archivedItemsList){
			if (item.getId().equals(uId)){
				target = item;
			}
		}
		return target;
	}
}
