package ca.ualberta.cs.jkeeling.a1todolist;
import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.jkeeling.a1todolist.Adapter.ItemAdapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
	private List<TDItem> allItemsList = new ArrayList<TDItem>();
	private EditText textBox; 
	private ItemAdapter adapter; 
	private Button optionsBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textBox = (EditText) findViewById(R.id.AddTextBox);
		optionsBtn = (Button) findViewById(R.id.OptionsButton);
		
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
	
	
	
	public void openSelector(String selectorType){
		Intent intent = new Intent(this, SelectorActivity.class);
		intent.putExtra("SELECTOR_TYPE", selectorType);
		startActivity(intent);
	}
	
	public void Add(View v){
		String text = textBox.getText().toString();
		TDItem item = new TDItem(text);
		allItemsList.add(item);		
		adapter.notifyDataSetChanged();
		textBox.setText("");			
	}
	
	public void OptionsMenu(View v){
		PopupMenu popup = new PopupMenu(this, optionsBtn);
		popup.getMenuInflater().inflate(R.menu.options_popup, popup.getMenu());
		popup.show();
	}
	
	public void toSummary(MenuItem i){
		
	}
	
	public void toArchive(MenuItem i){
		
	}
	
	public void toSelector(MenuItem i){
		if (i.getTitle().equals("Select for Archive")){
			openSelector("archive");
		}
		else if(i.getTitle().equals("Select for Email")){
			openSelector("email");
		}
	}
	
	public void archiveAll(MenuItem i){
		
	}
	
	public void emailAll(MenuItem i){
		
	}
	
	public void Delete(View v){
		View parent = (View)v.getParent();			
		CustomTextView text = (CustomTextView) parent.findViewById(R.id.textView);
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
