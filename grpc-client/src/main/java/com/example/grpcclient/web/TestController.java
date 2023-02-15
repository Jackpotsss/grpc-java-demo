package com.example.grpcclient.web;

import com.example.grpcclient.service.RpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/rpc")
public class TestController {

    @Autowired
    private RpcClientService rpcClientService;

    @RequestMapping("/client/sendMsgSimple")
    public String sendMsgSimple() throws Exception  {
        rpcClientService.sendMsgSimple();
        return "success";
    }
    @RequestMapping("/client/sendMsgSimpleAsync")
    public String sendMsgSimpleAsync() throws Exception  {
        rpcClientService.sendMsgSimpleAsync();
        return "success";
    }
    @RequestMapping("/client/sendMsgServer")
    public String sendMsgServer() throws Exception  {
        rpcClientService.sendMsgServer();
        return "success";
    }
    @RequestMapping("/client/sendMsgClient")
    public String sendMsgClient() throws Exception  {
        rpcClientService.sendMsgClient();
        return "success";
    }
    @RequestMapping("/client/sendMsgBoth")
    public String sendMsgBoth() throws Exception  {
        rpcClientService.sendMsgBoth();
        return "success";
    }

}
