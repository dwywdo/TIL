package me.dwywdo.armeria.lab.spring.boot.thrift.client;

import com.linecorp.armeria.client.thrift.ThriftClients;
import com.linecorp.armeria.common.thrift.ThriftSerializationFormats;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloRequest;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloResponse;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloService;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

public class HelloThriftServiceTest {
    @Test
    void getReply() throws TException {
        final HelloResponse response = helloService().hello(new HelloRequest("Euiyub"));
        System.out.println("response.getMessage() = " + response.getMessage());
    }

    @Test
    void getInternalErrorException() throws TException {
        final HelloResponse response = helloService().hello(new HelloRequest("exception"));
    }

    @Test
    void getIllegalArgumentException() throws TException {
        final HelloResponse response = helloService().hello(new HelloRequest("badName"));
    }

    private static HelloService.Iface helloService() {
        return ThriftClients.builder("http://127.0.0.1:20080")
                .path("/thrift/hello")
                .serializationFormat(ThriftSerializationFormats.BINARY)
                .build(HelloService.Iface.class);
    }
}
