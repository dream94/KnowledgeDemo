package com.example.cc.knowldegedemo.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2019/4/13.
 */

public class AidlService extends Service {
    private static final String TAG = AidlService.class.getSimpleName();

    private List<Man> manList;


    private ManManager.Stub stub = new ManManager.Stub() {
        @Override
        public List<Man> getMans() throws RemoteException {
            Log.e(TAG, "getMans");
            return manList;
        }

        @Override
        public void addMan(Man man) throws RemoteException {
            Log.e(TAG, "addMan man:" + man.toString());
            manList.add(man);
        }
    };

    public AidlService() {
        manList = new ArrayList<>();
        manList.add(new Man("aa", 11));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
