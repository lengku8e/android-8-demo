package com.example.sunhailong01.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MoviewView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent intent = new Intent(
				"com.jameschen.movie.ACTION_START_ACTIVITY");
		;
		startActivity(intent);
		finish();
	
	}
	
}