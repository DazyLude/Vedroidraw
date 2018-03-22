package com.sun.slava.sun;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;


import java.net.Socket;
import java.util.Timer;

import static java.lang.Math.negateExact;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor, mSensor2;
    private TextView ax, ay, az, qx, qy, qz;
    private Client mClient = null;

    private ToggleButton mConnect, Regime, Pause;
    private Button Point, New, Uni, End;
    private CheckBox pb, pe;
    private Spinner LPR, Size;
    private EditText Type;
    private SeekBar BarR, BarG, BarB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensor2 = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        //ax = (TextView) findViewById(R.id.textView7);
        //ay = (TextView) findViewById(R.id.textView2);
        az = (TextView) findViewById(R.id.textView1);

        //qx = (TextView) findViewById(R.id.textView2);
        //qy = (TextView) findViewById(R.id.textView5);
        //qz = (TextView) findViewById(R.id.textView6);

        mSensorManager.registerListener(listenerAcc, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(listenerGyr, mSensor, SensorManager.SENSOR_DELAY_NORMAL);





        mConnect = findViewById(R.id.connectbutton);
        Regime = findViewById(R.id.toggleButton1);
        Pause = findViewById(R.id.toggleButton2);

        Point = findViewById(R.id.button1);
        New = findViewById(R.id.button2);
        Uni = findViewById(R.id.button3);
        End = findViewById(R.id.button4);

        pb = findViewById(R.id.checkBox2);
        pe = findViewById(R.id.checkBox);

        LPR = findViewById(R.id.spinner1);
        Size = findViewById(R.id.spinner2);

        Type = findViewById(R.id.editText);

        BarR = findViewById(R.id.seekBar1);
        BarG = findViewById(R.id.seekBar2);
        BarB = findViewById(R.id.seekBar3);




        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if (mConnect.isChecked() && mClient == null) {
                    mClient = new Client();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                mClient.openConnection();
                            } catch (Exception e) {}

                        }
                    }).start();
                } else {
                    try{
                        mClient.sendData("DIS".getBytes());
                    } catch (Exception e) {}

                    mClient.closeConnection();
                    mClient = null;
                }
            }
        });


        Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mClient.sendData("P".getBytes());
                        } catch (Exception e) {}
                    }
                }).start();
            }
        });




        Uni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mClient.sendData("Uni".getBytes());
                        } catch (Exception e) {}
                    }
                }).start();
            }
        });

    }





    SensorEventListener listenerAcc = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(final SensorEvent event) {

            if (mConnect.isChecked()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            mClient.sendData(("[a,"+ SystemClock.uptimeMillis()+","+ String.valueOf(event.values[0]) + ',' + String.valueOf(event.values[1]) + ',' + String.valueOf(event.values[2]) + "]").getBytes());

                        } catch (Exception e) {
                        }
                    }
                }).start();

            }
        }
    };


    SensorEventListener listenerGyr = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(final SensorEvent event) {

            if (mConnect.isChecked()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            mClient.sendData(("[q,"+ SystemClock.uptimeMillis()+","+ String.valueOf(event.values[0]) + ',' + String.valueOf(event.values[1]) + ',' + String.valueOf(event.values[2]) + "]").getBytes());

                        } catch (Exception e) {
                        }
                    }
                }).start();

            }
        }
    };




}



