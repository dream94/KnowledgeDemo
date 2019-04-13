package com.example.cc.knowldegedemo.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.cc.knowldegedemo.R;

import java.util.List;

/**
 * Created by cc on 2019/4/13.
 */

public class AidlActivity extends AppCompatActivity {
    private static final String TAG = AidlActivity.class.getSimpleName();

    private ManManager manManager;

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
        bind();

    }

    public void bind() {
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
