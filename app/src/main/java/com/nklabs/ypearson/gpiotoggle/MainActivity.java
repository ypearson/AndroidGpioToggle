package com.nklabs.ypearson.gpiotoggle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private static boolean gpio_state = false;

    TextView textView1;
    Button   button1;

    private static final String TAG = "nklabs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate");
        textView1 = (TextView) findViewById(R.id.textView1);
        button1 = (Button)findViewById(R.id.button1);
        initgpio();
        setgpio(105);
        textView1.setText(Integer.toString(getgpio(1)));
        writegpio(GPIO_FILE, GPIO_LOW);
        textView1.setText(readgpio(GPIO_FILE));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gpio_state){
                    writegpio(GPIO_FILE, GPIO_LOW);
                    Toast.makeText(v.getContext(),"Toggle GPIO Low...",Toast.LENGTH_SHORT).show();
                    gpio_state = false;
                }
                else
                {
                    writegpio(GPIO_FILE, GPIO_HIGH);
                    Toast.makeText(v.getContext(),"Toggle GPIO High...",Toast.LENGTH_SHORT).show();
                    gpio_state = true;
                }
            }
        });


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
