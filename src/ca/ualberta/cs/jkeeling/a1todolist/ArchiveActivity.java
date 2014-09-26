package ca.ualberta.cs.jkeeling.a1todolist;
import java.util.ArrayList;
import java.util.List;
import ca.ualberta.cs.jkeeling.a1todolist.adapters.ItemAdapter;
import ca.ualberta.cs.jkeeling.a1todolist.data.FileDataManager;
import ca.ualberta.cs.jkeeling.a1todolist.models.CustomTextView;
import ca.ualberta.cs.jkeeling.a1todolist.models.TDItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

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
		generateArchiveList();
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
	
	private void generateArchiveList(){
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
	
	public void delete(View v){
		TDItem target = getTargetItem(v);
		archivedItemsList.remove(target);
		allItemsList.remove(target);
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
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
	
	public void optionsMenu(View v){
		PopupMenu popup = new PopupMenu(this, optionsBtn);
		popup.getMenuInflater().inflate(R.menu.archive_options_popup, popup.getMenu());
		popup.show();
	}
	
	public void toSummary(MenuItem i){
		Intent intent = new Intent(this, SummaryActivity.class);		
		startActivity(intent);
	}
	
	public void toMain(MenuItem i){
		Intent intent = new Intent(this, MainActivity.class);		
		startActivity(intent);
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
			item.setSelected(false);
			archivedItemsList.remove(item);
		}
		fdm.saveItems(allItemsList);
		adapter.notifyDataSetChanged();
	}
			
	public void emailAll(MenuItem i){
		for (TDItem item : allItemsList){
			item.setSelected(true);
		}
		emailSelected(i);
	}
	
	public void emailSelected(MenuItem i){	
		String bodyText = "To do List\n\n";
		bodyText = bodyText + "Active Items:\n";
		for (TDItem item : allItemsList){
			if(!item.getArchiveState()){
				if(item.getSelected()){
					if(item.getChecked()){
						bodyText = bodyText + "[x]";
					}
					else{
						bodyText = bodyText + "[ ]";
					}
					bodyText = bodyText + item.getName() + "/n";
				}
				item.setSelected(false);	
			}
		}
		bodyText = bodyText + "\nArchived Items:\n";
		for (TDItem item : archivedItemsList){
			if(item.getSelected()){
				if(item.getChecked()){
					bodyText = bodyText + "[x]";
				}
				else{
					bodyText = bodyText + "[ ]";
				}
				bodyText = bodyText + item.getName() + "\n";					
			}
			item.setSelected(false);
		}	
		
		sendEmail(bodyText);
	}
	
	private void sendEmail(String bodyText){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		intent.putExtra(Intent.EXTRA_SUBJECT, "To Do List");
		intent.putExtra(Intent.EXTRA_TEXT, bodyText);
		try {
		    startActivity(Intent.createChooser(intent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
	private List<TDItem> getSelected(){
		List<TDItem> selectedItemsList = new ArrayList<TDItem>();
		for (TDItem item : archivedItemsList){
			if (item.getSelected() == true){
				selectedItemsList.add(item);
			}
		}
		return selectedItemsList;
	}
	
	private TDItem getTargetItem(View v){
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
