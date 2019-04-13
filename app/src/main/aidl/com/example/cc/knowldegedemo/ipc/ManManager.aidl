// ManManager.aidl
package com.example.cc.knowldegedemo.ipc;

import com.example.cc.knowldegedemo.ipc.Man;

interface ManManager {
    List<Man> getMans();
    void addMan(in Man man);
}
