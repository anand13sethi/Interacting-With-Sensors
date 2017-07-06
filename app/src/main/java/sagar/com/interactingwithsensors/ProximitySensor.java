package sagar.com.interactingwithsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProximitySensor extends AppCompatActivity implements SensorEventListener {

    private TextView introTextProximity, proximityValue;
    private SensorManager mySensorManager;
    private Sensor mySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mySensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        introTextProximity = (TextView)findViewById(R.id.introTextProximity);
        proximityValue = (TextView)findViewById(R.id.proximityValue);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        proximityValue.setText("Value: " + sensorEvent.values[0]);
        if(sensorEvent.values[0] != mySensor.getMaximumRange()){
            Toast myToast = Toast.makeText(getApplicationContext(), "Object is close to the phone", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else{
            Toast myToast = Toast.makeText(getApplicationContext(), "Object is far away from phone", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //Not Used
    }
}
