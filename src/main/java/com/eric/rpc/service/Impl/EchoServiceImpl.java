package com.eric.rpc.service.Impl;

import com.eric.rpc.service.EchoService;

/**
 * Created by wangfeng on 16/10/16.
 */
public class EchoServiceImpl implements EchoService{

    @Override
    public String echo(String ping){
        return ping!=null?ping+"-->I am ok.":"I am ok.";
    }
}
