package ca.ualberta.cs.jkeeling.a1todolist;

import java.io.Serializable;
import java.util.UUID;

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
	
	public void setSelected(boolean bool){
		this.selected = bool;
	}
	
	public boolean getSelected(){
		return this.selected;
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
