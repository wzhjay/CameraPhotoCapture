package com.androidexample.cameraphotocapture;

import android.os.Bundle;
import android.view.Menu;

//import com.example.mapview.R;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.content.Intent;

//import Firstdroid.Gps.R;
import android.app.Service;
import android.view.View;
import android.widget.LinearLayout;


public class ViewMap extends MapActivity {
	
	private MapView map;
	private MapController controller;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_map);
		initMapView();
		initMyLocation();
	}
	
//	//@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_camera_photo_capture, menu);
//		return true;
//	}

	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void initMapView() {
		map = (MapView) findViewById(R.id.map);
		controller = map.getController();
		map.setSatellite(true);
		map.setBuiltInZoomControls(true);
	}

	/** Start tracking the position on the map. */
	private void initMyLocation() {
		final MyLocationOverlay overlay = new MyLocationOverlay(this, map);
		overlay.enableMyLocation();
		// overlay.enableCompass(); // does not work in emulator
		overlay.runOnFirstFix(new Runnable() {
			public void run() {
				// Zoom in to current location
				controller.setZoom(8);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		map.getOverlays().add(overlay);
	}


}
