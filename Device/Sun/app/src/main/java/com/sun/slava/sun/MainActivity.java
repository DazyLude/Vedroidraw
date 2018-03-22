package com.sun.slava.sun;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Socket;

import static java.lang.Math.negateExact;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor, mSensor2;
    private String Name = "";
    private int Port = 1500;
    private Socket sock = null;
    private TextView ax,ay,az,qx,qy,qz;
    private Button mButtonConnect = null;

    private Client mClient = new Client();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensor2 = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        //ax = (TextView) findViewById(R.id.textView7);
        //ay = (TextView) findViewById(R.id.textView2);
        //az = (TextView) findViewById(R.id.textView1);

        //qx = (TextView) findViewById(R.id.textView2);
        //qy = (TextView) findViewById(R.id.textView5);
        //qz = (TextView) findViewById(R.id.textView6);

        mSensorManager.registerListener(listenerAcc, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(listenerGyr, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        mButtonConnect = (Button) findViewById(R.id.connectbutton);




    }

    //public void Connect(View view) {
    //    Intent intent = new Intent(this, Connection.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
    //    startActivity(intent);
    //}

    public void Connect(View view) {
        //Intent intent = new Intent(this, Connection.class);
        //startActivity(intent);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mClient.openConnection();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //mButtonSend.setEnabled(true);
                            //mButtonClose.setEnabled(true);
                        }
                    });
                } catch (Exception e) {
                    //Log.e(LOG_TAG, e.getMessage());
                    mClient = null;
                }
            }
        }).start();

    }




    SensorEventListener listenerAcc = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //ax.setText(String.valueOf(floor(event.values[0]*1000)/1000));
            //ay.setText(String.valueOf(floor(event.values[1]*1000)/1000));
            //az.setText(String.valueOf(floor(event.values[2]*1000)/1000));
        }
    };


    SensorEventListener listenerGyr = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //qx.setText(String.valueOf(floor(event.values[0]*1000)/1000));
            //qy.setText(String.valueOf(floor(event.values[1]*1000)/1000));
            //qz.setText(String.valueOf(floor(event.values[2]*1000)/1000));
        }
    };








}



