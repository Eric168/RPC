package com.eric.rpc.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by wangfeng on 16/10/16.
 */
public class ExporterTask implements Runnable {
    Socket client=null;
    public ExporterTask(Socket client){
        this.client=client;
    }
    @Override
    public void run(){
        ObjectInputStream input=null;
        ObjectOutputStream output=null;
        try{
            input=new ObjectInputStream(client.getInputStream());
            String interfaceName=input.readUTF();
            Class<?>service=Class.forName(interfaceName);
            String methodName=input.readUTF();
            Class<?>[] parameterTypes=(Class<?>[])input.readObject();
            Object[] arguments=(Object[])input.readObject();
            Method method=service.getMethod(methodName,parameterTypes);
            Object result=method.invoke(service.newInstance(),arguments);
            output=new ObjectOutputStream(client.getOutputStream());
            output.writeObject(result);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(output!=null){
                try {
                    output.close();
                }catch (IOException e2){
                    e2.printStackTrace();
                }
            }
            if(input!=null){
                try{
                    input.close();
                }catch (IOException e3){
                    e3.printStackTrace();
                }
            }
            if(client!=null){
                try{
                    client.close();
                }catch (IOException e4){
                    e4.printStackTrace();
                }
            }
        }
    }
}
