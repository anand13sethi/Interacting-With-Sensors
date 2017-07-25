package sagar.com.sensors;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerSensor extends AppCompatActivity implements SensorEventListener {

    private View myView;
    private Sensor mySensor;
    private SensorManager mySM;
    private TextView xValue, yValue, zValue, infoText;
    private WindowManager myWM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_sensor);

        myWM = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        xValue = (TextView)findViewById(R.id.xValueId);
        yValue = (TextView)findViewById(R.id.yValueId);
        zValue = (TextView)findViewById(R.id.zValueId);
        infoText = (TextView)findViewById(R.id.infoTextId);

        mySM = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        if(mySM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
            Toast myToast = Toast.makeText(getApplicationContext(), "Accelerometer Not Present!", Toast.LENGTH_SHORT);
            myToast.show();
        }

        else{
            mySensor = mySM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xValue.setText("X: " + sensorEvent.values[0]);
        yValue.setText("Y: " + sensorEvent.values[1]);
        zValue.setText("Z: " + sensorEvent.values[2]);

        if(sensorEvent.values[2] > 9.0){
            infoText.setText("Device is Facing Upwards");
        }
        else if(sensorEvent.values[2] > -11.0 && sensorEvent.values[2] < -5.0){
            infoText.setText("Device is Upside Down");
        }
        else if(sensorEvent.values[1] > 7.0){
            infoText.setText("Device is Upright");
        }
        else if((sensorEvent.values[0] > 7.0) || (sensorEvent.values[0] > -10.0 && sensorEvent.values[0] < -7.0)){
            infoText.setText("Device is in Landscape Orientation");
        }
       //colorChange(sensorEvent);

    }

    private void colorChange(SensorEvent event){
        float [] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        xValue.setText("X: " + x);
        yValue.setText("Y: "+ y);
        zValue.setText("Z: "+ z);

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

    @Override
    protected void onPause() {
        super.onPause();
        mySM.unregisterListener(this);
    }
}
