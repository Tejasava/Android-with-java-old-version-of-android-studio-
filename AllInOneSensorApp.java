package com.example.user.allinonesensoraap;

import android.bluetooth.BluetoothAdapter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AllInOneSensorApp extends AppCompatActivity implements SensorEventListener {
    private ImageView iv;
    private MediaPlayer mp;
    private CameraManager cm;
    private WifiManager wm;
    private BluetoothAdapter ba;
    private SensorManager sm;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components and system services
        iv = findViewById(R.id.imageView);
        mp = MediaPlayer.create(this, R.raw.s);
        cm = (CameraManager) getSystemService(CAMERA_SERVICE);
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        ba = BluetoothAdapter.getDefaultAdapter();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor listener
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        if (Math.abs(x) > 1 || Math.abs(y) > 1 || Math.abs(z) > 1) {
            mp.start();
            wm.setWifiEnabled(true);
            ba.enable();
            iv.setImageResource(R.drawable.on);
            Toast.makeText(AllInOneSensorApp.this, "Phone moved", Toast.LENGTH_SHORT).show();
            try {
                String cameraId = cm.getCameraIdList()[0];
                cm.setTorchMode(cameraId, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            mp.pause();
            wm.setWifiEnabled(false);
            ba.disable();
            iv.setImageResource(R.drawable.off);
            try {
                String cameraId = cm.getCameraIdList()[0];
                cm.setTorchMode(cameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Do nothing
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
