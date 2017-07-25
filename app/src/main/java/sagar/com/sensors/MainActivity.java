package sagar.com.sensors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainIntrotext, introIcon;
    private Button accelerometerButton, gyroscopeButton, proximityButton, cameraButton, sensorListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Making App go Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/MaterialIcons-Regular.ttf");

        mainIntrotext = (TextView)findViewById(R.id.mainIntroId);
        introIcon = (TextView)findViewById(R.id.introIconId);
        accelerometerButton = (Button)findViewById(R.id.accelerometerButtonId);
        gyroscopeButton = (Button)findViewById(R.id.gyroscopeButtonId);
        proximityButton = (Button)findViewById(R.id.proximityButtonId);
        cameraButton = (Button)findViewById(R.id.cameraButtonId);
        sensorListButton = (Button)findViewById(R.id.listId);

        introIcon.setTypeface(fontFamily);
        accelerometerButton.setTypeface(fontFamily);
        gyroscopeButton.setTypeface(fontFamily);
        cameraButton.setTypeface(fontFamily);
        proximityButton.setTypeface(fontFamily);
        sensorListButton.setTypeface(fontFamily);

        //Accelerometer Button Function
        accelerometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accelerometerIntent = new Intent(MainActivity.this, AccelerometerSensor.class);
                startActivity(accelerometerIntent);
            }
        });

        //Gyroscope Button Function
        gyroscopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gyroscopeIntent = new Intent(MainActivity.this, GyroscopeSensor.class);
                startActivity(gyroscopeIntent);
            }
        });

        //Proximity Button Function
        proximityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proximityIntent = new Intent(MainActivity.this, ProximitySensor.class);
                startActivity(proximityIntent);
            }
        });

        //Camera Button Function
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MainActivity.this, CameraSensor.class);
                startActivity(cameraIntent);
            }
        });

        //Sensor List Button
        sensorListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(MainActivity.this, ListSensor.class);
                startActivity(listIntent);            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
