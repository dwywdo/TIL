namespace java me.dwywdo.armeria.lab.spring.boot.thrift.idl

enum ErrorCode {
    INTERNAL_ERROR = 0,
    ILLEGAL_ARGUMENT = 1
}

exception HelloException {
    1: ErrorCode code,
    2: string errorMessage,
}

struct HelloRequest {
    1: string name,
}

struct HelloResponse {
    1: string message,
}

service HelloService {
    HelloResponse hello(1: HelloRequest request) throws (1: HelloException helloException)
}
