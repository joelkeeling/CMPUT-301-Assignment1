package ca.ualberta.cs.jkeeling.a1todolist.data;
import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.jkeeling.a1todolist.models.TDItem;

public interface IDataManager {
	public ArrayList<TDItem> loadItems();
	public void saveItems(List<TDItem> lts); 
}
