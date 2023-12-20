package me.dwywdo.armeria.spring.boot.thrift.service;

import me.dwywdo.armeria.lab.spring.boot.thrift.idl.ErrorCode;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloException;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloRequest;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloResponse;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloService;

public class HelloThriftServiceHandler implements HelloService.Iface {
    @Override
    public HelloResponse hello(HelloRequest request) throws HelloException {
        final String inputName = request.name;

        if ("thriftException".equals(inputName)) {
            throw new HelloException(ErrorCode.INTERNAL_ERROR, "Internal Error");
        }

        if ("badName".equals(inputName)) {
            throw new HelloException(ErrorCode.ILLEGAL_ARGUMENT, "Illegal Argument");
        }

        if ("runtimeException".equals(inputName)) {
            throw new RuntimeException();
        }
        
        return new HelloResponse(String.format("Hello, %s! This message is from Armeria annotated service!", inputName));
    }
}
