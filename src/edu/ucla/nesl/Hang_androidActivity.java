package edu.ucla.nesl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Hang_androidActivity extends Activity {
	private static final String TAG = "Hang_androidActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "onCreate");
        startService(new Intent(this, LocationCollectionService.class));
    }
}