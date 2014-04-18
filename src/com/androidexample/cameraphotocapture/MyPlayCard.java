package com.androidexample.cameraphotocapture;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fima.cardsui.objects.RecyclableCard;

public class MyPlayCard extends RecyclableCard {
	
	String temple_img;
	Bitmap image  = null;
	ImageView iv;
	Boolean from_server;
	
	public MyPlayCard(String titlePlay, String description, String color,
			String titleColor, Boolean hasOverflow, Boolean isClickable, String temple_img_url, Boolean fromServer) {
		super(titlePlay, description, color, titleColor, hasOverflow,
				isClickable);
		temple_img = temple_img_url;
		from_server = fromServer;
	}

	@Override
	protected int getCardLayoutId() {
		return R.layout.card_play;
	}

	@Override
	protected void applyTo(View convertView) {
		((TextView) convertView.findViewById(R.id.title)).setText(titlePlay);
		((TextView) convertView.findViewById(R.id.title)).setTextColor(Color
				.parseColor(titleColor));
		((TextView) convertView.findViewById(R.id.description))
				.setText(description);
		((ImageView) convertView.findViewById(R.id.stripe))
				.setBackgroundColor(Color.parseColor(color));
		
		iv = (ImageView) convertView.findViewById(R.id.temple_img);
		new TheTask().execute();

		if (isClickable == true)
			((LinearLayout) convertView.findViewById(R.id.contentLayout))
					.setBackgroundResource(R.drawable.selectable_background_cardbank);

		if (hasOverflow == true)
			((ImageView) convertView.findViewById(R.id.overflow))
					.setVisibility(View.VISIBLE);
		else
			((ImageView) convertView.findViewById(R.id.overflow))
					.setVisibility(View.GONE);
	}
	
	class TheTask extends AsyncTask<Void,Void,Void>
	{

	    @Override
	    protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	    }


	    @Override
	    protected Void doInBackground(Void... params) {
	        // TODO Auto-generated method stub
	        try
	        {

	        if(from_server) {
	        	image = downloadBitmap(temple_img);
	        } else {
	        	image = BitmapFactory.decodeFile(temple_img); 
	        }	
	        
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        // TODO Auto-generated method stub
	        super.onPostExecute(result);
	        if(image != null)
	        {
	           iv.setImageBitmap(image);
	        }

	    }   
	}
	 private Bitmap downloadBitmap(String url) {
	     // initilize the default HTTP client object
	     final DefaultHttpClient client = new DefaultHttpClient();
	     Bitmap newImage = null;
	     
	     //forming a HttoGet request 
	     final HttpGet getRequest = new HttpGet(url);
	     try {

	         HttpResponse response = client.execute(getRequest);

	         //check 200 OK for success
	         final int statusCode = response.getStatusLine().getStatusCode();

	         if (statusCode != HttpStatus.SC_OK) {
	             Log.w("ImageDownloader", "Error " + statusCode + 
	                     " while retrieving bitmap from " + url);
	             return null;

	         }

	         final HttpEntity entity = response.getEntity();
	         if (entity != null) {
	             InputStream inputStream = null;
	             try {
	                 // getting contents from the stream 
	                 inputStream = entity.getContent();

	                 // decoding stream data back into image Bitmap that android understands
	                 image = BitmapFactory.decodeStream(inputStream);
	                 newImage = Bitmap.createScaledBitmap(image, 130, 200, true); 

	             } finally {
	                 if (inputStream != null) {
	                     inputStream.close();
	                 }
	                 entity.consumeContent();
	             }
	         }
	     } catch (Exception e) {
	         // You Could provide a more explicit error message for IOException
	         getRequest.abort();
	         Log.e("ImageDownloader", "Something went wrong while" +
	                 " retrieving bitmap from " + url + e.toString());
	     } 

	     return newImage;
	 }
}