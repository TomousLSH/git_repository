package com.example.demo.curator;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.SleepUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;

public class ZkUtil {
    private static String connectionString="127.0.0.1";

    

    public static void main(String[] args){
        ZkUtil zkUtil = new ZkUtil();
        //zkUtil.createNode();
        //zkUtil.checkExist();
        //zkUtil.getChildren();
        //zkUtil.updateNode();



        //zkUtil.nodeWatch();


        //zkUtil.nodeCache();
        //zkUtil.pathChildrenCache();
        //zkUtil.treeCache();

        zkUtil.lock();

        SleepUtil.sleep(Integer.MAX_VALUE);
    }

    public void createNode(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        //RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //CuratorFramework client = ZkClientFactory.createWithOptions(connectionString,retryPolicy,10000,50000);
        client.start();

        try {
            String s = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .inBackground(backgroundCallback())
                    .forPath("/order/num_", "hellos".getBytes());
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // client.close();
        }



    }

    public BackgroundCallback backgroundCallback(){
        return new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("接受回调");
                System.out.println(JSON.toJSONString(curatorEvent));
            }
        };
    }


    public void checkExist(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();

        try {
            Stat stat = client.checkExists().inBackground(backgroundCallback()).forPath("/dubbo");
            System.out.println(JSON.toJSONString(stat));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // client.close();
        }
    }
    
    public void getChildren(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();

        try {
            List<String> list = client.getChildren().inBackground(backgroundCallback()).forPath("/");
            System.out.println(JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // client.close();
        }
    }

    public void updateNode(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();

        try {
            byte[] bytes = client.getData().forPath("/order");
            System.out.println("order数据:"+new String(bytes));

            Stat oder = client.setData().inBackground(backgroundCallback()).forPath("/order", "order-new1".getBytes());
            System.out.println(JSON.toJSONString(oder));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // client.close();
        }
    }



    private void nodeWatch(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();


        try {
            byte[] bytes = client.getData().forPath("/order");
            System.out.println("order数据:"+new String(bytes));

            client.getData().usingWatcher(new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //只能监听一次
                    System.out.println("接收到监听");
                    System.out.println(JSON.toJSONString(watchedEvent));
                }
            }).forPath("/order");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // client.close();
        }
    }


    /**
     *  @author: lsh
     *  @Date: 2019/6/14 0014
     *  @Description: 节点监听缓存
     */
    private void nodeCache(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();


        NodeCache nodeCache = new NodeCache(client, "/node_gawa");

        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("nodeCache监听到变化");
                ChildData childData = nodeCache.getCurrentData();
                if(null!=childData){
                    System.out.println("data:"+new String(childData.getData()));
                    System.out.println("path:"+childData.getPath());
                    System.out.println("stat:"+JSON.toJSONString(childData.getStat()));
                }else{
                    System.out.println("删除节点:"+JSON.toJSON(childData));
                }

            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SleepUtil.sleep(5000);
//
//        try {
//
//            System.out.println("第一次改变数据");
//            client.setData().forPath("/node_cache", "aa111".getBytes());
//            SleepUtil.sleep(5000);
//
//            System.out.println("第二次改变数据");
//            client.setData().forPath("/node_cache", "bb111".getBytes());
//            SleepUtil.sleep(5000);
//
//            System.out.println("第三次改变数据");
//            client.setData().forPath("/node_cache", "cc111".getBytes());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    /**
     *  @author: lsh
     *  @Date: 2019/6/14 0014
     *  @Description: PathChildrenCache 子节点监听
     */
    private void pathChildrenCache(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();

        PathChildrenCache cache = new PathChildrenCache(client, "/node_gawa", true);

        PathChildrenCacheListener listener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("pathChildrenCache监听到变化");
                System.out.println("type:"+pathChildrenCacheEvent.getType());
                ChildData childData = pathChildrenCacheEvent.getData();
                if(null!=childData){
                    if(null!=childData.getData()){
                        System.out.println("nodedata:"+new String(childData.getData()));
                    }else {
                        System.out.println("nodedata:"+ "&nbsp;");
                    }

                    System.out.println("path:"+childData.getPath());
                    System.out.println("stat:"+JSON.toJSONString(childData.getStat()));
                }else{
                    System.out.println("***节点:"+JSON.toJSON(childData));
                }
            }
        };

        cache.getListenable().addListener(listener);
        try {
            cache.start(PathChildrenCache.StartMode.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *  @author: lsh
     *  @Date: 2019/6/14 0014
     *  @Description: Tree Cache 节点树缓存
     */
    private void treeCache() {
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();

        TreeCache treeCache = new TreeCache(client, "/node_gawa");
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.err.println("treeCache接收到监听");
                System.out.println("type:"+treeCacheEvent.getType());

                ChildData childData = treeCacheEvent.getData();
                if(null!=childData){
                    System.out.println("data:"+new String(childData.getData()));
                    System.out.println("path:"+childData.getPath());
                    System.out.println("stat:"+JSON.toJSONString(childData.getStat()));
                }else {
                    System.out.println("childData null:"+JSON.toJSONString(childData));
                }

            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        try {
            treeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void lock(){
        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
        client.start();


        InterProcessMutex interProcessMutex = new InterProcessMutex(client, "/testLock");
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 1000; i++ ){

            System.out.println(i);
        }

        SleepUtil.sleep(3600000);
        try {
            interProcessMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
