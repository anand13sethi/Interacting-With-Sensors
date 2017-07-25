package sagar.com.sensors;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class ProximitySensor extends AppCompatActivity implements SensorEventListener {

    private TextView proximityIntroText, proximityValue;
    private Sensor mySensor;
    private SensorManager mySM;
    private WindowManager myWM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        myWM = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/MaterialIcons-Regular.ttf");

        proximityIntroText = (TextView)findViewById(R.id.proximityIntroId);
        proximityValue = (TextView)findViewById(R.id.proximityValueId);

        mySM = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        if(mySM.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null){
            Toast.makeText(getApplicationContext(), "Proximity Sensor is Not Available On This Device.", Toast.LENGTH_SHORT).show();
        }
        else{
            mySensor = mySM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED,
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        float[] value = sensorEvent.values;
        proximityValue.setText("Value: " + value[0]);
        if(value[0] < mySensor.getMaximumRange()){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("#3f51b5"));
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("#673AB7"));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mySM.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySM.registerListener(this, mySensor, 2*1000*1000);
    }
}
