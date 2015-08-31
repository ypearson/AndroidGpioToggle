package com.nklabs.ypearson.gpiotoggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {System.loadLibrary("gpio");}
    public native void initgpio();
    public native String readgpio(String path);
    public native void writegpio(String path, String val);
    public native void setgpio(int gpioNum);
    public native int getgpio(int gpioNum);

    private static final String GPIO_FILE = "/sdcard/gpio.dev";
    private static final String GPIO_HIGH = "HIGH";
    private static final String GPIO_LOW = "LOW";

    TextView textView1;

    private static final String TAG = "nklabs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate");
        textView1 = (TextView) findViewById(R.id.textView1);
        initgpio();
        setgpio(105);
        textView1.setText(Integer.toString(getgpio(1)));
        writegpio(GPIO_FILE, GPIO_LOW);
        textView1.setText(readgpio(GPIO_FILE));


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
