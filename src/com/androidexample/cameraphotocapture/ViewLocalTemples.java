package com.androidexample.cameraphotocapture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fima.cardsui.views.CardUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ViewLocalTemples extends Activity {

	// private TextView templeName;
	private static CardUI mCardView;
	private static ArrayList<Temple> temples = null;
	private static Context ctx;
	static TempleDBHandler db;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.view_temple);
	    ctx = getBaseContext();
	    
	    db = new TempleDBHandler(ctx);
	    
	    mCardView = (CardUI) findViewById(R.id.cardsview);
		mCardView.setSwipeable(false);
		
	    thread.start(); 
	    // draw cards
	 	mCardView.refresh();
	}
	
	Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {
            try {
                //Your code goes here
            	Log.v("LocalTemple", "before get temples");
            	temples = getTemples();
            	Log.v("LocalTemple", "after get temples");
            	buildCardView();
            	
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
	
	public static ArrayList<Temple> getTemples() {
		ArrayList<Temple> temples = db.getAllTemples();
	    return temples;
	}
	
	public static void buildCardView() {
		// init CardView
	    String[] stripeColors = {"#33B5E5", "#AA66CC", "#99CC00", "#FFBB33", "#FF4444"};
    	String[] titleColors = {"#0099CC", "#9933CC", "#669900", "#FF8800", "#CC0000", "#222222", "#e00707", "#9d36d0"};
    	
    	Log.v("LocalTemple", "start");
    	Log.v("LocalTemple", temples.size() + "");
		// request feeds
		if(temples.size() > 0){
    		try {
    			for (int i = 0; i < temples.size(); i++) {
    				Temple temple = temples.get(i);
    				String id = temple.getID();
    				String name  = temple.getTempleName();
    				String type = temple.getType();
    				String god = temple.getDeities();
    				String dialect = temple.getDialect();
    				
    				Log.v("LocalTemple", name + type + god + dialect);
    				
    				String temple_img_url = Environment.getExternalStorageDirectory()+ "/Temples/" + id + ".png";
    				Log.v("LocalTemple", "img_url: " + temple_img_url);
    				
    				// card config
    				String title = name + "\r\n";
    				String content = "Type: " + type + "\r\n";
    				content += "God of worship: " + god + "\r\n";
    				content += "Dialect: " + dialect + "\r\n";
    				String stripeColor = stripeColors[(int) (Math.random()*4)];
    				String titleColor = titleColors[(int) (Math.random()*7)];
    				Boolean hasOverflow = false;
    				Boolean isClickable = true;
    				Boolean fromServer = false;
    				
    				mCardView
    				.addCard(new MyPlayCard(title, content, stripeColor, titleColor, hasOverflow, isClickable, temple_img_url, fromServer));
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
	}
}
