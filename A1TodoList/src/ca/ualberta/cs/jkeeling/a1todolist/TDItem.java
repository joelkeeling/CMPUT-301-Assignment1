package ca.ualberta.cs.jkeeling.a1todolist;

import java.util.UUID;

public class TDItem {
	private boolean checked;
	private boolean selected;
	private String name;	
	private String id;
	
	public TDItem(String name){
		this.name = name;
		this.checked = false;
		this.selected = false;
		this.id = UUID.randomUUID().toString();
	}
	
	public void setChecked(boolean bool){
		this.checked = bool;
	}
	
	public boolean getChecked(){
		return this.checked;
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
}
