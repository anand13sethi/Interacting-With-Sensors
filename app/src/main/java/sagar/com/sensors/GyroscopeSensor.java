package sagar.com.sensors;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class GyroscopeSensor extends AppCompatActivity implements SensorEventListener{

    private Sensor mySensor;
    private SensorManager mySM;
    private TextView gyroscopeIntrotext, xValue, yValue, zValue;
    private WindowManager myWM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_sensor);

        myWM = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gyroscopeIntrotext = (TextView)findViewById(R.id.gyroscopeIntroId);
        xValue = (TextView)findViewById(R.id.xValueId);
        yValue = (TextView)findViewById(R.id.yValueId);
        zValue = (TextView)findViewById(R.id.zValueId);

        mySM = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        if(mySM.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null){
            Toast.makeText(getApplicationContext(), "Gyroscope Sensor Not Available on This Device", Toast.LENGTH_SHORT).show();
        }
        else{
            mySensor = mySM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        xValue.setText("X: " + x);
        yValue.setText("Y: " + y);
        zValue.setText("Z: " + z);

        if(z > 0.5f){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
        else if(z < 0.5f){
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mySM.unregisterListener(this);
    }
}
