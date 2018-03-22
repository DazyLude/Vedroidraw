package com.sun.slava.sun;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.Math.floor;

public class Connection extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor, mSensor2;
    TextView ax,ay,az,qx,qy,qz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensor2 = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        ax = (TextView) findViewById(R.id.textView);
        ay = (TextView) findViewById(R.id.textView2);
        az = (TextView) findViewById(R.id.textView1);

        qx = (TextView) findViewById(R.id.textView2);
        qy = (TextView) findViewById(R.id.textView5);
        qz = (TextView) findViewById(R.id.textView6);

    }


    public void onClickSens(View v) {
        mSensorManager.registerListener(listenerAcc, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(listenerGyr, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //@Override
    //protected void onPause() {
    //    super.onPause();
    //    mSensorManager.unregisterListener(listenerAcc, mSensor);
    //}

    SensorEventListener listenerAcc = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            ax.setText(String.valueOf(floor(event.values[0]*1000)/1000));
            ay.setText(String.valueOf(floor(event.values[1]*1000)/1000));
            az.setText(String.valueOf(floor(event.values[2]*1000)/1000));
        }
    };


    SensorEventListener listenerGyr = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            qx.setText(String.valueOf(floor(event.values[0]*1000)/1000));
            qy.setText(String.valueOf(floor(event.values[1]*1000)/1000));
            qz.setText(String.valueOf(floor(event.values[2]*1000)/1000));
        }
    };

}

