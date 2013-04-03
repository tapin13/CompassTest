package com.example.compasstest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class CompassTest extends Activity implements SensorEventListener {

	TextView textView;
	StringBuilder builder = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		setContentView(textView);
		
		SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).size() == 0) {
			textView.setText("No accelerometer installed");
		} else {
			Sensor light = manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0);
			if(!manager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)) {
				textView.setText("Couldn't register sensor listener");
			}
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("Geomagnetic field strength along the X axis: ");
		builder.append(event.values[0]);
		builder.append("μT");
		builder.append("\n");
		builder.append("Geomagnetic field strength along the Y axis:");
		builder.append(event.values[1]);
		builder.append("μT");
		builder.append("\n");
		builder.append("Geomagnetic field strength along the Z axis:");
		builder.append(event.values[2]);
		builder.append("μT");
		builder.append("\n");
		textView.setText(builder.toString());
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// nothing to do ;)
	}

}
