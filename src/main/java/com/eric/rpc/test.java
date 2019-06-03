package com.eric.rpc;

import com.eric.rpc.service.EchoService;
import com.eric.rpc.service.Impl.EchoServiceImpl;
import com.eric.rpc.service.RpcExporter;
import com.eric.rpc.service.RpcImporter;

import java.net.InetSocketAddress;

/**
 * Created by wangfeng on 16/10/16.
 */
public class test {
    public static void main(String[] args)throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RpcExporter.exporter("localhost",8088);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        RpcImporter<EchoService> importer = new RpcImporter<EchoService>();
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
        System.out.println(echo.echo("are you ok?"));

    }
}
