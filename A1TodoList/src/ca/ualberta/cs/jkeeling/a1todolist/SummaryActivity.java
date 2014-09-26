package ca.ualberta.cs.jkeeling.a1todolist;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.jkeeling.a1todolist.data.FileDataManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends Activity {	
	private List<TDItem> allItemsList = new ArrayList<TDItem>();
	private List<TDItem> activeItemsList = new ArrayList<TDItem>();
	private List<TDItem> archivedItemsList = new ArrayList<TDItem>();
	private FileDataManager fdm;
	private Button optionsBtn;
	private TextView active;
	private TextView activeChecked;
	private TextView activeUnchecked;
	private TextView archived;
	private TextView archivedChecked;
	private TextView archivedUnchecked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		fdm = new FileDataManager(this.getApplicationContext());
		active = (TextView) findViewById(R.id.active);
		activeChecked = (TextView) findViewById(R.id.activeChecked);
		activeUnchecked= (TextView) findViewById(R.id.activeUnchecked);
		archived= (TextView) findViewById(R.id.archived);
		archivedChecked= (TextView) findViewById(R.id.archivedChecked);
		archivedUnchecked= (TextView) findViewById(R.id.archivedUnchecked);
		
		generateSummary();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
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
	
	public void generateSummary(){
		allItemsList = fdm.loadItems();		
		for (TDItem item : allItemsList){
			if (item.getArchiveState() == false){
				activeItemsList.add(item);
			}
			else{
				archivedItemsList.add(item);
			}
		}
		setActive();
		setArchived();
		
	}
	
	public void setActive(){
		Integer cCounter = 0;
		Integer uCounter = 0;
		for (TDItem item : activeItemsList){
			if (item.getChecked()){
				cCounter++;
			}
			else {
				uCounter++;
			}
		}
		active.setText("Number of items on the list: " + activeItemsList.size());
		activeChecked.setText("Items complete: " + cCounter.toString());
		activeUnchecked.setText("Items left to do: " + uCounter.toString());
	}
	
	public void setArchived(){
		Integer cCounter = 0;
		Integer uCounter = 0;
		for (TDItem item : archivedItemsList){
			if(item.getChecked()){
				cCounter++;
			}
			else{
				uCounter++;
			}
		}
		archived.setText("Number of archived items: " + archivedItemsList.size());
		archivedChecked.setText("Archived items complete: " + cCounter.toString());
		archivedUnchecked.setText("Archived items incomplete: " + uCounter.toString());		
	}
	
}
