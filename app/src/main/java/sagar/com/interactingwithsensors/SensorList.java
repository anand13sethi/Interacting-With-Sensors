package sagar.com.interactingwithsensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorList extends AppCompatActivity {

    private TextView introText, sensorList;
    private SensorManager myManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        introText = (TextView)findViewById(R.id.introText1);
        sensorList = (TextView)findViewById(R.id.sensorList);
        myManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> deviceSensors = myManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s: deviceSensors){
            sensorList.append("\n\n" + s.getName());
        }
    }
}
