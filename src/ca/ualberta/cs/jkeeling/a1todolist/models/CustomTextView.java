package ca.ualberta.cs.jkeeling.a1todolist.models;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

// This class was created so that TextViews could store important IDs
public class CustomTextView extends TextView{
	private String uniqueId;
	public CustomTextView(Context context) {
		super(context);			
	}
	public CustomTextView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}
	public void setUId(String id){
		this.uniqueId = id;
	}
	public String getUId(){
		return this.uniqueId;
	}
}