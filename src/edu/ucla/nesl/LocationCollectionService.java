package edu.ucla.nesl;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocationCollectionService extends Service {

	private static final String TAG = "Hang_androidActivity";
	private FileWriter mFileWriter;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(TAG, "service onStart");
		LocationManager locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mListener);
		locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mListener);
		Toast.makeText(LocationCollectionService.this, "HELLO", Toast.LENGTH_SHORT).show();
	}

	LocationListener mListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			String debugString = "Location changed: " + location.getLatitude() + " " + location.getLongitude();
			Log.d(TAG, debugString);
			Toast.makeText(LocationCollectionService.this, debugString, Toast.LENGTH_SHORT).show();
			
			
			File root = Environment.getExternalStorageDirectory();
			
			try {
				mFileWriter = new FileWriter(new File(root, "HangFile.txt"));
				mFileWriter.write("Start " + System.currentTimeMillis());
				mFileWriter.write(debugString+"\n");
				mFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(LocationCollectionService.this, "FAILED WRITING", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
	};
}
