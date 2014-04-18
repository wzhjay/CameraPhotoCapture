package com.androidexample.cameraphotocapture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ViewServerTemples extends Activity {

	// private TextView templeName;
	private static CardUI mCardView;
	private static String temples = "";
	private static Context ctx;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.view_temple);
	    ctx = getBaseContext();
	    
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
            	temples = getTemples();
            	buildCardView();
            	
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
	
	public static String getTemples() {
	    StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet("http://172.23.180.80/getAllTemples.php");
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        String line;
	        while ((line = rd.readLine()) != null) {
	          builder.append(line);
	        }
	      } else {
	        Log.e("TempleGet", "Failed to call api");
	      }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    Log.i("Temple", builder.toString());
	    return builder.toString();
	}
	
	public static void buildCardView() {
		// init CardView
	    String[] stripeColors = {"#33B5E5", "#AA66CC", "#99CC00", "#FFBB33", "#FF4444"};
    	String[] titleColors = {"#0099CC", "#9933CC", "#669900", "#FF8800", "#CC0000", "#222222", "#e00707", "#9d36d0"};
    	
		// request feeds
		if(temples.length() > 0){
    		try {
    			JSONObject jsonObject = new JSONObject(temples);
    			JSONArray jsonArray = jsonObject.getJSONArray("temples");
    			for (int i = 0; i < jsonArray.length(); i++) {
    				JSONObject temple = jsonArray.getJSONObject(i);
    				int temple_id = Integer.parseInt(temple.optString("temple_id"));
    				String chi_name  = temple.optString("chi_name");
    				String hypy_name  = temple.optString("hypy_name");
    				String off_name = temple.optString("off_name");
    				String const_yr = temple.optString("const_yr");
    				String god = temple.optString("god");
    				String dialect = temple.optString("dialect");
//    				String temple_img_url = "http://137.132.165.161/singapore/db_img/1333888659DSCF5268.JPG";
    				String temple_img_url = "http://samui.sawadee.com/temples/24img/241chinesetemple.jpg";
    				
    				// card config
    				String title = chi_name + "\r\n" + hypy_name;
    				String content = "Official name: " + off_name + "\r\n";
    				content += "Contructed in: " + const_yr + "\r\n";
    				content += "God of worship: " + god + "\r\n";
    				content += "Dialect: " + dialect + "\r\n";
    				String stripeColor = stripeColors[(int) (Math.random()*4)];
    				String titleColor = titleColors[(int) (Math.random()*7)];
    				Boolean hasOverflow = false;
    				Boolean isClickable = true;
    				Boolean fromServer = true;
    				
    				mCardView
    				.addCard(new MyPlayCard(title, content, stripeColor, titleColor, hasOverflow, isClickable, temple_img_url, fromServer));
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
	}
}
