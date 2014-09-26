package ca.ualberta.cs.jkeeling.a1todolist.models;

import java.io.Serializable;
import java.util.UUID;

// A class for To Do items. 
// Holds all of the information that is required for its use in all of the activities
// For the most part the important attributes for display reasons are archived, checked and selected. 
public class TDItem implements Serializable{
	private boolean archived;
	private boolean checked;
	private boolean selected;
	private String name;	
	private String id;
	
	public TDItem(String name){
		this.name = name;
		this.checked = false;
		this.selected = false;
		this.archived = false;
		this.id = UUID.randomUUID().toString();
	}
	
	public void setChecked(boolean bool){
		this.checked = bool;
	}
	
	public boolean getChecked(){
		return this.checked;
	}
	
	public boolean toggleChecked(){
		if (this.checked == true){
			this.checked = false;
			return false;
		}
		else {
			this.checked = true;
			return true;
		}
	}	
	
	public void setSelected(boolean bool){
		this.selected = bool;
	}
	
	public boolean getSelected(){
		return this.selected;
	}
	
	public boolean toggleSelected(){
		if (this.selected == true){
			this.selected = false;
			return false;
		}
		else {
			this.selected = true;
			return true;
		}		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void archive(){
		this.archived = true;
	}
	
	public void unarchive(){
		this.archived = false;
	}
	
	public boolean getArchiveState(){
		return this.archived;
	}
}
