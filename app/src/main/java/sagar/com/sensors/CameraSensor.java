package sagar.com.sensors;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class CameraSensor extends AppCompatActivity {

    private Button captureButton, saveButton, discardButton;
    private TextView cameraIntroText;
    private ImageView image;
    private WindowManager myWM;
    Bitmap picture;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_sensor);

        //Make app fullscreen
        myWM = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/MaterialIcons-Regular.ttf");

        //Adding widgets
        captureButton = (Button)findViewById(R.id.captureButtonId);
        image = (ImageView)findViewById(R.id.imageId);
        saveButton = (Button)findViewById(R.id.saveButtonId);
        discardButton = (Button)findViewById(R.id.discardButtonId);
        cameraIntroText = (TextView)findViewById(R.id.cameraIntroId);

        captureButton.setTypeface(fontFamily);
        saveButton.setTypeface(fontFamily);
        discardButton.setTypeface(fontFamily);
        cameraIntroText.setTypeface(fontFamily);


        saveButton.setEnabled(false);
        discardButton.setEnabled(false);

        //Check if camera available
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            captureButton.setEnabled(false);
            Toast.makeText(getApplicationContext(), "There Is An Issue With The Camera.", Toast.LENGTH_SHORT).show();
        }
        else {
            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(captureIntent, REQUEST_IMAGE_CAPTURE);
                    saveButton.setEnabled(true);
                    discardButton.setEnabled(true);
                }
            });
        }

        //Save Bitmap
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis()/1000;
                String timeStamp = tsLong.toString();
                saveImage(picture, timeStamp);
            }
        });

        //Discard Bitmap
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageDrawable(null);
            }
        });
    }

    //Displaying captured image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ){

            Bundle extras = data.getExtras();
            picture = (Bitmap) extras.get("data");
            image.setImageBitmap(picture);
        }
    }

    private void saveImage(Bitmap finalBitmap, String image_name) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
