package com.exception.sendposition;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class XYPosition extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    //private Sensor mOrientation;
    private Sensor mAccelerometer;
    TextView x;
    TextView y;
    TextView z;
    Boolean read = false;
    float xval=0;
    float yval=0;
    float zval=0;

    Button values;
    String TAG="Post: ";
    private float[] accelerometerValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xyposition);
        x = (TextView)findViewById(R.id.x);
        y = (TextView)findViewById(R.id.y);
        z = (TextView)findViewById(R.id.z);
        values = (Button)findViewById(R.id.values);
        Log.e(TAG, "1");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.e(TAG, "2");
        Log.e(TAG, "3");
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        Log.e(TAG, "4");
        values.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               read=true;


            }
        });

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    @Override
    protected void onResume() {
        super.onResume();
       // mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.e(TAG, "On sensor changed");
     /*   if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            return;
        }
        accelerometerValues = event.values.clone();
     */

       float azimuth_angle = event.values[0];
        float pitch_angle = event.values[1];
        float roll_angle = event.values[2];

        if(read==true){
            xval=azimuth_angle;
            yval=pitch_angle;
            zval=roll_angle;
            read=false;
            x.setText("x: " + azimuth_angle);
            y.setText("y: " + pitch_angle);
            z.setText("z: " + roll_angle);
        }

        // Do something with these orientation angles.
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_xyposition, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
