package sagar.com.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

public class ListSensor extends AppCompatActivity {

    private TextView introText, sensorList;
    private SensorManager mySM;
    private WindowManager myWM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensor);

        myWM = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        introText = (TextView)findViewById(R.id.sensorListId);
        sensorList = (TextView)findViewById(R.id.allSensorsId);
        mySM = (SensorManager)getSystemService(SENSOR_SERVICE);

        List<Sensor> deviceSensors = mySM.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s: deviceSensors) {
            sensorList.append("\n\n" + s.getName());
        }
    }
}
