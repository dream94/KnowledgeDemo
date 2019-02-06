package com.example.cc.knowldegedemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.cc.knowldegedemo.auto.A$$Test;
import com.example.cc.utils.AppUtils;
import com.example.cc.utils.TimeUtils;
import com.internal.Print;

@Print(text = "aaa")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        test();
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("11")
                        .setCancelable(true)
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {

                            }
                        });
                builder.create().show();
            }
        });

        A$$Test test = new A$$Test();
        Log.e("dream", test.getMessage());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("dream", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("dream", "onResume");
    }

    private void test() {
        TimeUtils.testDemo();
        AppUtils.testDemo(this);
    }
}
