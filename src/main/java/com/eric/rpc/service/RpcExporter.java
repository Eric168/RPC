package com.eric.rpc.service;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by wangfeng on 16/10/16.
 */
public class RpcExporter {
    static Executor executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public  static  void exporter(String hostname,int port)throws Exception{
        ServerSocket server=new ServerSocket();
        server.bind(new InetSocketAddress(hostname,port));
        try{
            while(true){
                executor.execute(new ExporterTask(server.accept()));
            }
        }finally {
            server.close();
        }
    }
}
