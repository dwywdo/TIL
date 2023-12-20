package me.dwywdo.armeria.spring.boot.thrift;

import com.google.common.collect.ImmutableList;
import com.linecorp.armeria.common.RpcResponse;
import com.linecorp.armeria.common.metric.MeterIdPrefixFunction;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.metric.MetricCollectingService;
import com.linecorp.armeria.server.thrift.THttpService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.ErrorCode;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloException;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloRequest;
import me.dwywdo.armeria.lab.spring.boot.thrift.idl.HelloService;
import me.dwywdo.armeria.spring.boot.thrift.service.HelloThriftServiceHandler;
import org.apache.thrift.TException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Configuration
public class ArmeriaServerConfiguration {
    @Bean
    ArmeriaServerConfigurator serverConfigurator() {
        return serverBuilder -> {
            serverBuilder.serviceUnder("/docs", DocService.builder().exampleRequests(ImmutableList.of(new HelloService.hello_args(new HelloRequest("John Doe")))).build());
            serverBuilder.decorator(LoggingService.newDecorator());
            serverBuilder.accessLogWriter(AccessLogWriter.combined(), false);
            serverBuilder
                    .service("/thrift/hello", THttpService.of(new HelloThriftServiceHandler()))
                    .decorator(MetricCollectingService.builder(
                            MeterIdPrefixFunction.ofDefault("armeria.server").withTags("service", HelloThriftServiceHandler.class.getSimpleName())
                    ).successFunction((context, log) -> {
                        final RpcResponse rpcResponse = (RpcResponse) log.responseContent();
                        return isSuccess(Objects.requireNonNull(rpcResponse), HelloException.class, ImmutableList.of(ErrorCode.INTERNAL_ERROR));
                    }).newDecorator());
        };
    }

    public static <T extends TException, E extends Enum<E>> boolean isSuccess(RpcResponse rpcResponse, Class<T> exceptionType, List<E> failureErrorCodeList) {
        if (rpcResponse.cause() == null) {
            return true;
        }

        if (exceptionType.isInstance(rpcResponse.cause())) {
            try {
                final Field codeField = Objects.requireNonNull(rpcResponse.cause()).getClass().getDeclaredField("code");
                codeField.setAccessible(true);
                final Object actualCode = codeField.get(rpcResponse.cause());

                return !failureErrorCodeList.contains(actualCode);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
