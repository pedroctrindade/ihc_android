package com.example.exercicioihc3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor accelerometer;

    private TextView textX;
    private TextView textY;
    private TextView textZ;

    private double acceleration = 0.0f;
    private double accelerationCurrent = SensorManager.GRAVITY_EARTH;
    private double accelerationLast = SensorManager.GRAVITY_EARTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        textZ = findViewById(R.id.textZ);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double sensorX = event.values[0];
        double sensorY = event.values[1];
        double sensorZ = event.values[2];

        // Me baseei aqui https://stackoverflow.com/questions/14574879/how-to-detect-movement-of-an-android-device
        textX.setText(String.valueOf(sensorX));
        textY.setText(String.valueOf(sensorY));
        textZ.setText(String.valueOf(sensorZ));


        accelerationLast = accelerationCurrent;
        accelerationCurrent = Math.sqrt(sensorX * sensorX + sensorY * sensorY + sensorZ * sensorZ);

        double delta = accelerationCurrent - accelerationLast;
        acceleration = acceleration * 0.9f + delta;



        if (acceleration > 2){
            Intent intent = new Intent( this, MainActivity2.class);

            startActivity(intent);

            finish();

        }
    }
}
