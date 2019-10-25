package com.example.cc.knowldegedemo.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.cc.knowldegedemo.R;
import com.example.cc.view.CenterDrawableTextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cc on 2019/4/13.
 */

public class AidlActivity extends AppCompatActivity {
    private static final String TAG = AidlActivity.class.getSimpleName();

    private ManManager manManager;
    private CenterDrawableTextView tvCenter;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            manManager = ManManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        tvCenter = findViewById(R.id.tv_center);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manManager != null) {
                    try {
                        List<Man> list = manManager.getMans();
                        for (Man man : list) {
                            Log.e(TAG, man.toString());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manManager != null) {
                    try {
                        manManager.addMan(new Man("bb", 22));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        findViewById(R.id.btn3).setOnClickListener(v -> {
//            startActivity(new Intent(AidlActivity.this, SpGridRecycleViewActivity.class));
            tvCenter.setText("asdasd");
            Drawable drawable = getResources().getDrawable(R.drawable.ic_send_message);
            drawable.setBounds(0, 0, 40, 40);
            tvCenter.setCompoundDrawables(drawable, null, null, null);
        });
        bind();
        testOther();


        String qrCodeUrl = "https://www.baidu.com/?type=建行";
        try {
            qrCodeUrl = new String(qrCodeUrl.getBytes(), "UTF-8");
            Log.e("dream", "encode:" + URLEncoder.encode(qrCodeUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void testOther() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
        try {
            Date date = sdf.parse("2007-12-25");
            Log.e("dream", "data:" + sdf1.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Log.e("dream", "data:" + s);
//        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date date= format.parse("");

    }
}
