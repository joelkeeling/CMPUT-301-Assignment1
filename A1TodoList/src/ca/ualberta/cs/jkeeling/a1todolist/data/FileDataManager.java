package ca.ualberta.cs.jkeeling.a1todolist.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import ca.ualberta.cs.jkeeling.a1todolist.TDItem;

public class FileDataManager implements IDataManager{
	private String filename;
	private Context context;
	
	public FileDataManager(Context context){
		this.context = context;
		this.filename = this.context.getFilesDir().getAbsolutePath() + "/todoItemData.sav";
	}
	
	public ArrayList<TDItem> loadItems() {
		ArrayList<TDItem> items = new ArrayList<TDItem>();

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);

			items = (ArrayList<TDItem>) ois.readObject();

		} catch (Exception e) {
			Log.i("LonelyTwitter", "Error casting");
			e.printStackTrace();
		} 

		return items;
	}
	/*
	public void saveItems(List<TDItem> items) {
		try {
			File f = new File(FILENAME);
			if (!f.exists()){
				String path = f.getPath();
				f.createNewFile();				
			}
			
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(items);
			fos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	public void saveItems(List<TDItem> items) {
		FileOutputStream fos;		
		try {		
			File file = new File(filename);
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(items);
			fos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}