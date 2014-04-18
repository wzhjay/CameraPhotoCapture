package com.androidexample.cameraphotocapture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;


import com.google.android.maps.MapView;

//import com.androidexample.cameraphotocapture.CameraPhotoCapture.LoadImagesFromSDCard;

public class CameraPhotoCapture extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {

	final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
	
    	Uri imageUri                      = null;
        static TextView imageDetails      = null;
        public  static ImageView showImg  = null;
         
  	 CameraPhotoCapture CameraActivity = null;

	private LocationClient mLocationClient;

	private boolean isLocationServiceReady;
    
	private TextView templeName;
	private TextView type;
	private TextView mainDeity;
	private TextView dialect;
	private TextView builderSurname;
	private TextView worships;
	private TextView contact;
	private TextView others;
	private TextView info;
	private String pathLastPhoto;
	
	MapView myMap;
    //private MyLocationOverlay myLocOverlay;
	
	String templeID = "";
	final Context ctx = this;
	final TempleDBHandler db = new TempleDBHandler(ctx);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_photo_capture);
		
		//create local folder to store pics
		File storagePath = new File(Environment.getExternalStorageDirectory() + "/Temples");
		boolean success = true;
		if(!storagePath.exists()) {
			success = storagePath.mkdir();
		}
		if(success) {
			;//
		} else {
			;//
		}
		
        /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
        mLocationClient = new LocationClient(this, this, this);
        
		
		CameraActivity = this;
		
		imageDetails = (TextView) findViewById(R.id.imageDetails);
		
		showImg = (ImageView) findViewById(R.id.showImg);
		
		final Button photo = (Button) findViewById(R.id.photo);
		final Button recapture = (Button) findViewById(R.id.recapture);
		final Button viewPic = (Button) findViewById(R.id.viewPic);
		final Button mapButton = (Button) findViewById(R.id.mapButton);
		final Button viewLocalTemples = (Button) findViewById(R.id.databasebutton2);
		final Button viewServerTemples = (Button) findViewById(R.id.databasebutton3);
		final Button saveLocal = (Button) findViewById(R.id.sendPhoto);
		
		//capture first image after entering the app
		photo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
	 	       /***** Define the file-name to save photo taken by Camera activity *******/
		        String fileName = "Camera_Example.jpg";
		        
		        // Create parameters for Intent with filename
		      
		        
		        ContentValues values = new ContentValues();
		        
		        values.put(MediaStore.Images.Media.TITLE, fileName);
		        
		        values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
		        
		        /****** imageUri is the current activity attribute, define and save it for later usage  *****/
		        imageUri = getContentResolver().insert(
		        		MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		        
		        /******   EXTERNAL_CONTENT_URI : style URI for the "primary" external storage volume. ******/

		        
		        /******  Standard Intent action that can be sent to have the camera application capture an image and return it. ******/ 
		        
		        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		        
		         intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		         
		         intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		         
		         
		         
		        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		       
			}	
			
		});
		
		//capture more images
		recapture.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
		 	       /***** Define the file-name to save photo taken by Camera activity *******/
			        String fileName = "Camera_Example.jpg";
			        
			        // Create parameters for Intent with filename
			        
			        ContentValues values = new ContentValues();
			        
			        values.put(MediaStore.Images.Media.TITLE, fileName);
			        
			        values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
			        
			        /****** imageUri is the current activity attribute, define and save it for later usage  *****/
			        imageUri = getContentResolver().insert(
			        		MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			        
			        /******   EXTERNAL_CONTENT_URI : style URI for the "primary" external storage volume. ******/

			        
			        /******  Standard Intent action that can be sent to have the camera application capture an image and return it. ******/ 
			        
			        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			        
			         intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			         
			         intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			         
			         
			         
			        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			       
				}	
			
		});
		
		
		viewPic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//add your code here..
				info = (TextView) findViewById(R.id.info);
				templeName = (TextView) findViewById(R.id.templeName);
				type = (TextView) findViewById(R.id.type);
				mainDeity = (TextView) findViewById(R.id.mainDeity);
				dialect = (TextView) findViewById(R.id.dialect);
				builderSurname = (TextView) findViewById(R.id.builderSurname);
				worships = (TextView) findViewById(R.id.worships);
				contact = (TextView) findViewById(R.id.contact);
				others = (TextView) findViewById(R.id.others);
				EditText enterTempleName = (EditText)findViewById(R.id.enterTempleName);
				EditText enterType = (EditText)findViewById(R.id.enterType);
				EditText enterDeity = (EditText)findViewById(R.id.enterDeity);
				EditText enterDialect = (EditText)findViewById(R.id.enterDialect);
				EditText enterSurname = (EditText)findViewById(R.id.enterSurname);
				EditText templeInfo = (EditText)findViewById(R.id.templeInfo);
				EditText enterContact = (EditText)findViewById(R.id.enterContact);
				EditText enterOthers = (EditText)findViewById(R.id.enterOthers);
				
			    String Info = info.getText().toString();
		        String TempleName = templeName.getText().toString();
		        String Type = type.getText().toString();
		        String MainDeity = mainDeity.getText().toString();
		        String Dialect = dialect.getText().toString();
		        String BuilderSurname = builderSurname.getText().toString();
		        String Worships = worships.getText().toString();
		        String Contact = contact.getText().toString();
		        String Others = others.getText().toString();
		        String EnterTempleName = enterTempleName.getText().toString();
		        String EnterType = enterType.getText().toString();
		        String EnterDeity = enterDeity.getText().toString();
		        String EnterDialect = enterDialect.getText().toString();
		        String EnterSurname = enterSurname.getText().toString();
		        String TempleInfo = templeInfo.getText().toString();
		        String EnterContact = enterContact.getText().toString();
		        String EnterOthers = enterOthers.getText().toString();
			
				Intent mIntent = new Intent(CameraPhotoCapture.this, ViewPhoto.class);
				Bundle extras = new Bundle();
				extras.putString("info", Info);
				extras.putString("name", TempleName);
				extras.putString("type", Type);
				extras.putString("mainDeity", MainDeity);
				extras.putString("dialect", Dialect);
				extras.putString("builderSurname", BuilderSurname);
				extras.putString("worships", Worships);
				extras.putString("contact", Contact);
				extras.putString("others", Others);
				extras.putString("enterTempleName", EnterTempleName);
				extras.putString("enterType", EnterType);
				extras.putString("enterDeity", EnterDeity);
				extras.putString("enterDialect", EnterDialect);
				extras.putString("enterSurname", EnterSurname);
				extras.putString("templeInfo", TempleInfo);
				extras.putString("enterContact", EnterContact);
				extras.putString("enterOthers", EnterOthers);
				extras.putString("path", pathLastPhoto);
				
				mIntent.putExtras(extras);
				CameraPhotoCapture.this.startActivity(mIntent);
			}
			
		});
		

		mapButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View m){
              
				Intent intent = new Intent(CameraPhotoCapture.this,ViewMap.class );
	            startActivity(intent);     
		    }});

		
//		mapButton.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//			
//				Intent mIntent = new Intent(CameraPhotoCapture.this, ViewPhoto.class);
//				
//				mIntent.putExtras(mIntent);
//				CameraPhotoCapture.this.startActivity(mIntent);
//				
//			}
//		});
		
		viewServerTemples.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		        
				Intent myIntent0 = new Intent(CameraPhotoCapture.this, ViewServerTemples.class);
				CameraPhotoCapture.this.startActivity(myIntent0);
			}
		});
		
		viewLocalTemples.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		        
				Intent myIntent0 = new Intent(CameraPhotoCapture.this, ViewLocalTemples.class);
				CameraPhotoCapture.this.startActivity(myIntent0);
			}
		});
		
		saveLocal.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//add your code here..

				EditText enterTempleName = (EditText)findViewById(R.id.enterTempleName);
				EditText enterType = (EditText)findViewById(R.id.enterType);
				EditText enterDeity = (EditText)findViewById(R.id.enterDeity);
				EditText enterDialect = (EditText)findViewById(R.id.enterDialect);
				EditText enterSurname = (EditText)findViewById(R.id.enterSurname);
				EditText enterWorship = (EditText)findViewById(R.id.templeInfo);
				EditText enterContact = (EditText)findViewById(R.id.enterContact);
				EditText enterOthers = (EditText)findViewById(R.id.enterOthers);
			    
			    Location mCurrentLocation = mLocationClient.getLastLocation();
			    final String ID = templeID;
			    final String lat = mCurrentLocation.getLatitude() + "";
			    final String lon = mCurrentLocation.getLongitude() + "";
		        final String templeName = enterTempleName.getText().toString();
		        final String type = enterType.getText().toString();
		        final String deities = enterDeity.getText().toString();
		        final String dialect = enterDialect.getText().toString();
		        final String builderName = enterSurname.getText().toString();
		        final String worships = enterWorship.getText().toString();
		        final String contact = enterContact.getText().toString();
		        final String others = enterOthers.getText().toString();
		        
		        AlertDialog.Builder saveDialog = new AlertDialog.Builder(ctx);

		        // Setting Dialog Title
		        saveDialog.setTitle("Create New Temple");

		        // Setting Dialog Message
		        saveDialog.setMessage("Save to local?");

		        // Setting Icon to Dialog
		        saveDialog.setIcon(R.drawable.ic_launcher);

		        // Setting Positive "Yes" Btn
		        saveDialog.setPositiveButton("YES",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                        // Write your code here to execute after dialog
		                        Toast.makeText(getApplicationContext(),
		                                "Temple info Saved!", Toast.LENGTH_SHORT)
		                                .show();
		                        db.addTemple(new Temple(ID,
		        		        		templeName,
		        		        		type,
		        		        		deities,
		        		        		dialect,
		        		        		builderName,
		        		        		worships,
		        		        		contact,
		        		        		others,
		        		        		lon,
		        		        		lat
		        		        		));
		                    }
		                });
		        // Setting Negative "NO" Btn
		        saveDialog.setNegativeButton("NO",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                        // Write your code here to execute after dialog
		                        Toast.makeText(getApplicationContext(),
		                                "Not save yet!", Toast.LENGTH_SHORT)
		                                .show();
		                        dialog.cancel();
		                    }
		                });

		        // Showing Alert Dialog
		        saveDialog.show();
			}
			
		});
	}

	
	protected void onStart(){
//		mLocationClient.connect();
		super.onStart();
	}
	
    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        isLocationServiceReady = false;
        super.onStop();
    }
    
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	    {
	    	if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	    		
	    	    if (resultCode == RESULT_OK) {
	    	    	
	    	    	String imageId = convertImageUriToFile(imageUri,CameraActivity);
	    	    	
	    	    	new LoadImagesFromSDCard().execute(""+imageId);

	    	    	mLocationClient.connect();

	    	    } else if (resultCode == RESULT_CANCELED) {
	    	    	
	    	        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
	    	    } else {
	    	    	
	    	        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
	    	    }
	    	}
	    }
	 
	 
	 public static String convertImageUriToFile (Uri imageUri, Activity activity)  {
	    	Cursor cursor = null;
	    	int imageID = 0;
	    	
	    	try {
	    	    /*********** Which columns values want to get *******/
	    		String [] proj={
	    				         MediaStore.Images.Media.DATA, 
	    				         MediaStore.Images.Media._ID,
	    				         MediaStore.Images.Thumbnails._ID, 
	    				         MediaStore.Images.ImageColumns.ORIENTATION
	    				       };
	    	    
	    		cursor = activity.managedQuery( 
	    				
			    				imageUri,   // Get data for specific image URI
			    	            proj,       // Which columns to return
			    	            null,       // WHERE clause; which rows to return (all rows)
			    	            null,       // WHERE clause selection arguments (none)
			    	            null        // Order-by clause (ascending by name)
			    	            
			    	         );      
	    	    
	    	    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
	    	    int columnIndexThumb = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
	    	    int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    	    //int orientation_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION);
	    	    
	    	    int size = cursor.getCount();
	    	    
	            /*******  If size is 0, there are no images on the SD Card. *****/
	    	    
	            if (size == 0) {
	            	imageDetails.setText("No Image");
	            }
	            else
	            {
	    	   
		    	    int thumbID = 0;
		    	    if (cursor.moveToFirst()) {
		    	    	
		    	    	/**************** Captured image details ************/
		    	    	
		    	    	/*****  Used to show image on view in LoadImagesFromSDCard class ******/
		    	    	imageID = cursor.getInt(columnIndex);
		    	    	
		    	    	thumbID   = cursor.getInt(columnIndexThumb);
		    	    	
		    	    	String Path = cursor.getString(file_ColumnIndex);
		    	    	
		    	    	//String orientation =  cursor.getString(orientation_ColumnIndex);
		    	    	
		    	    	String CapturedImageDetails =// " Image Details : \n"
		    	    		         //                     +" Image ID: "+imageID+"\n"
		    	    		        //                      +" ThumbID :"+thumbID+"\n"+
		    	    		                              " Path:"+Path+"\n";
		    	    	// Show Captured Image detail on view
		    	    	imageDetails.setText(CapturedImageDetails);
		    	    }
	            }    
	    	} finally {
	    	    if (cursor != null) {
	    	      //  cursor.close();
	    	        cursor = null;
	    	    }
	    	}
	    	
	    	return ""+imageID;
	    }
	 
	 
	     /**
	     * Async task for loading the images from the SD card. 
	     * 
	     * @author Android Example
	     *
	     */
	    // Class with extends AsyncTask class
	    public class LoadImagesFromSDCard  extends AsyncTask<String, Void, Void> {
	        
	        private ProgressDialog Dialog = new ProgressDialog(CameraPhotoCapture.this);
	        
	        Bitmap mBitmap;
	        
	        protected void onPreExecute() {
	            /****** NOTE: You can call UI Element here. *****/  
	            //UI Element
	            Dialog.setMessage("Loading image from Sdcard..");
	            Dialog.show();
	        }

	        // Call after onPreExecute method
	        protected Void doInBackground(String... urls) {
	        	
	        	Bitmap bitmap = null;
	            Bitmap newBitmap = null;
	            Uri uri = null;                  
	                try {
	                	
	                	/**  Uri.withAppendedPath Method Description
	                	* Parameters
	                	*    baseUri  Uri to append path segment to 
	                	*    pathSegment  encoded path segment to append 
                        * Returns
	                	*    a new Uri based on baseUri with the given segment appended to the path
	                	*/
	  	                uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + urls[0]);
	  	                
	  	                /**************  Decode an input stream into a bitmap. *********/
	                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
	                    
	                    if (bitmap != null) {
	                    	
	                    	/********* Creates a new bitmap, scaled from an existing bitmap. ***********/

	                        newBitmap = Bitmap.createScaledBitmap(bitmap, 500, 300, true); // **
	                        
	                        bitmap.recycle();
	                        
	                        if (newBitmap != null) {       	
	                        	mBitmap = newBitmap;
	                        	saveBitmapToFolder(mBitmap);
	                        }
	                    }
	                } catch (IOException e) {
	                    //Error fetching image, try to recover            	
	                	/********* Cancel execution of this task. **********/
	                	cancel(true);
	                }
	            
	            return null;
	        }
	     // save the bitmp pic to desired folder
	        public void saveBitmapToFolder(Bitmap bm) {
	        	long lDateTime = new Date().getTime();
	        	String fileName = lDateTime + ".png";
	        	templeID = lDateTime + "";
	        	pathLastPhoto = Environment.getExternalStorageDirectory()+ "/Temples/" + fileName;
				File file = new File(pathLastPhoto);
	            FileOutputStream fos = null;
	            try {
	            	fos = new FileOutputStream(file);
	                if (fos != null) {
	                	bm.compress(Bitmap.CompressFormat.PNG, 90, fos);
	                	fos.close();
	                }
	        	} catch (IOException e) {	
	        	}
	        }
	        
	        protected void onPostExecute(Void unused) {
	        	
	            // NOTE: You can call UI Element here.
	            
	            // Close progress dialog
	            Dialog.dismiss();
	            
	            if(mBitmap != null)
	              showImg.setImageBitmap(mBitmap);
	            
	        }
	        
	    }
	    
//	    public void sendMessage(View view) {
	        // Do something in response to button
	//    	Intent intent = new Intent(this, DisplayMessageActivity.class);
	  //      EditText editText = (EditText) findViewById(R.id.edit_message);
	    //    String message = editText.getText().toString();
	      //  intent.putExtra(EXTRA_MESSAGE, message);
	        //startActivity(intent);
//	    }

	    /*
	     * Called by Location Services when the request to connect the
	     * client finishes successfully. At this point, you can
	     * request the current location or start periodic updates
	     */
	    @Override
	    public void onConnected(Bundle dataBundle) {
	        // Display the connection status
	        isLocationServiceReady = true;
	    	Location mCurrentLocation = mLocationClient.getLastLocation();
	        TextView info = (TextView) findViewById(R.id.info);
	        info.setText(String.format("Location : %.3f , %.3f", mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
	       
	        
	        templeName = (TextView) findViewById(R.id.templeName);
	        templeName.setVisibility(View.VISIBLE);
	        
	        EditText enterTempleName = (EditText) findViewById(R.id.enterTempleName);
	        enterTempleName.setVisibility(View.VISIBLE);
	        
	        type = (TextView) findViewById(R.id.type);
	        type.setVisibility(View.VISIBLE);
	        
	        EditText enterType = (EditText) findViewById(R.id.enterType);
	        enterType.setVisibility(View.VISIBLE);
	        
	        mainDeity = (TextView) findViewById(R.id.mainDeity);
	        mainDeity.setVisibility(View.VISIBLE);
	        
	        EditText enterDeity = (EditText) findViewById(R.id.enterDeity);
	        enterDeity.setVisibility(View.VISIBLE);
	        
	        dialect = (TextView) findViewById(R.id.dialect);
	        dialect.setVisibility(View.VISIBLE);
	        
	        EditText enterDialect = (EditText) findViewById(R.id.enterDialect);
	        enterDialect.setVisibility(View.VISIBLE);
	        
	        builderSurname = (TextView) findViewById(R.id.builderSurname);
	        builderSurname.setVisibility(View.VISIBLE);
	        
	        EditText enterSurname = (EditText) findViewById(R.id.enterSurname);
	        enterSurname.setVisibility(View.VISIBLE);
	        
	        
	        worships = (TextView) findViewById(R.id.worships);
	        worships.setVisibility(View.VISIBLE);
	        
	        EditText templeInfo = (EditText) findViewById(R.id.templeInfo);
	        templeInfo.setVisibility(View.VISIBLE);
	        
	        contact = (TextView) findViewById(R.id.contact);
	        contact.setVisibility(View.VISIBLE);

	        EditText enterContact = (EditText) findViewById(R.id.enterContact);
	        enterContact.setVisibility(View.VISIBLE);
	        
	        others = (TextView) findViewById(R.id.others);
	        others.setVisibility(View.VISIBLE);
	 
	        EditText enterOthers = (EditText) findViewById(R.id.enterOthers);
	        enterOthers.setVisibility(View.VISIBLE);
	        
	        Button sendPhoto = (Button) findViewById(R.id.sendPhoto);
	        sendPhoto.setVisibility(View.VISIBLE); 
	        Button databaseButton = (Button) findViewById(R.id.databaseButton);
	        databaseButton.setVisibility(View.VISIBLE); 
	        Button recapture = (Button) findViewById(R.id.recapture);
	        recapture.setVisibility(View.VISIBLE); 
	        Button viewPic = (Button) findViewById(R.id.viewPic);
	        viewPic.setVisibility(View.VISIBLE); 
	        Button mapButton = (Button) findViewById(R.id.mapButton);
	        mapButton.setVisibility(View.VISIBLE); 
	        
	        
	        Button photo = (Button) findViewById(R.id.photo);
	        photo.setVisibility(View.GONE); 
	        TextView message1 = (TextView) findViewById(R.id.message1);
	        message1.setVisibility(View.GONE);
	        
	        Button databasebutton2 = (Button) findViewById(R.id.databasebutton2);
	        databasebutton2.setVisibility(View.GONE); 
	        
	        Button databasebutton3 = (Button) findViewById(R.id.databasebutton3);
	        databasebutton2.setVisibility(View.GONE); 
	       

	    }
	    
	    /*
	     * Called by Location Services if the connection to the
	     * location client drops because of an error.
	     */
	    @Override
	    public void onDisconnected() {
	        // Display the connection status
	        Toast.makeText(this, "Disconnected. Please re-connect.",
	                Toast.LENGTH_SHORT).show();
	    }

	    /*
	     * Called by Location Services if the attempt to
	     * Location Services fails.
	     */
	    @Override
	    public void onConnectionFailed(ConnectionResult connectionResult) {
	    }
	    
}
