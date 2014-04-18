package com.androidexample.cameraphotocapture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPhoto extends Activity {

//	private TextView templeName;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    setContentView(R.layout.activity_view_photo);
	    
	    Intent intent = getIntent();
	    
	    TextView info = (TextView) findViewById(R.id.info);
	    info.setText(intent.getExtras().getString("info"));
	    
	    TextView templeName = (TextView) findViewById(R.id.templeName);
	    templeName.setText(intent.getExtras().getString("name"));
	  
	    TextView enterTempleName = (TextView)findViewById(R.id.enterTempleName);
	    enterTempleName.setText(intent.getExtras().getString("enterTempleName"));
	    
	    TextView type = (TextView) findViewById(R.id.type);
	    type.setText(intent.getExtras().getString("type"));
	    
	    TextView enterType = (TextView)findViewById(R.id.enterType);
	    enterType.setText(intent.getExtras().getString("enterType"));
	   
	    TextView mainDeity = (TextView) findViewById(R.id.mainDeity);
	    mainDeity.setText(intent.getExtras().getString("mainDeity"));

	    TextView enterDeity = (TextView)findViewById(R.id.enterDeity);
	    enterDeity.setText(intent.getExtras().getString("enterDeity"));
	    
	    TextView dialect = (TextView) findViewById(R.id.dialect);
	    dialect.setText(intent.getExtras().getString("dialect"));
	    
	    TextView enterDialect = (TextView)findViewById(R.id.enterDialect);
	    enterDialect.setText(intent.getExtras().getString("enterDialect"));
	    
	    TextView builderSurname = (TextView) findViewById(R.id.builderSurname);
	    builderSurname.setText(intent.getExtras().getString("builderSurname"));
	    
	    TextView enterSurname = (TextView)findViewById(R.id.enterSurname);
	    enterSurname.setText(intent.getExtras().getString("enterSurname"));

	    TextView worships = (TextView) findViewById(R.id.worships);
	    worships.setText(intent.getExtras().getString("worships"));
	    
	    TextView templeInfo = (TextView)findViewById(R.id.templeInfo);
	    templeInfo.setText(intent.getExtras().getString("templeInfo"));
	    
	    TextView contact = (TextView) findViewById(R.id.contact);
	    contact.setText(intent.getExtras().getString("contact"));
	    
	    TextView enterContact = (TextView)findViewById(R.id.enterContact);
	    enterContact.setText(intent.getExtras().getString("enterContact"));
	    
	    TextView others = (TextView) findViewById(R.id.others);
	    others.setText(intent.getExtras().getString("others"));
	    
	    TextView enterOthers = (TextView)findViewById(R.id.enterOthers);
	    enterOthers.setText(intent.getExtras().getString("enterOthers"));
	    
	    //show image
	    String path = intent.getExtras().getString("path");
	    try {
	    	File file = new File(path);
	    	Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
	    	((ImageView) findViewById(R.id.showImg)).setImageBitmap(Bitmap.createScaledBitmap(bitmap, 500, 300, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
