package com.example.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

public class LockUtil {
    private static String connectionString="127.0.0.1";
    private static CuratorFramework client = null;
    private InterProcessMutex interProcessMutex = null;
    static {
        client = ZkClientFactory.createSimple(connectionString);
        client.start();
    }



    public void lock(String lockPath){
        interProcessMutex = new InterProcessMutex(client,lockPath);
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unlock(){
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
