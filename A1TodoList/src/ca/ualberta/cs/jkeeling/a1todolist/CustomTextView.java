package ca.ualberta.cs.jkeeling.a1todolist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

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