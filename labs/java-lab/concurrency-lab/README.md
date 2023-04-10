# Labs for Java CompletableFuture
On this lab, I've tried...
- Investigate various methods to handle result or exception from `CompletableFuture<T>`
  - handle()
  - whenComplete()
  - exceptionally()
- Summarize each API's specification
```java
public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn);
```
- Summarize appropriate use cases for each APIs

| Use Case                    | API             | Description                                                                                                                              |
|-----------------------------|-----------------|------------------------------------------------------------------------------------------------------------------------------------------|
| Logging                     | whenComplete()  | whenComplete() method just consumes the success and failure as callback, without translating the outcomes                                |
| Exception-Only              | exceptionally() | exceptionally() method only takes exception as input. Normal result is out of concern                                                    |
| Exception-Only w/o Recovery | exceptionally() | When chaining 2 dependent completableFutures, the recovery of first dependent's exceptionally() does not affect the second dependent     |
| Transformation              | handle()        | handle() method takes care both result and exception. Also it can return type U (It's bi-function<? super T, Throwable, ? extends U> fn. |

- Add sample sources for each use cases
  - Logging
  ```java
  cf.whenComplete((result, ex) -> {
    if (ex != null) {
        logger.error("Execution failed", ex);
    } else {
        logger.info("Execution completed: {}", result);
    }
  });
  ```
  - Exception-Only w/o Recovery
  ```java
  var cf = asyncCode();
  // dependent 1
  cf.exceptionally(ex -> {
    logger.error("Something failed", ex);
    return null;
  });
  // dependent 2
  cf.thenApply(user -> "Hi, " + user);;
  ```
  - Transformation
  ```java
  // CompletableFuture<User> to CompletableFuture<Response>
  cf.handle((user, ex) -> {
    if (ex != null) {
        return Response.failure("Unknown user");
    } else {
        return Response.success(user);
    }
  }
  ``` 


