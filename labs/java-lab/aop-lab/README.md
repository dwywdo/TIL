# Lab for Java AOP
On this lab, I've tried...
- Practice Java's AOP implementation, without Spring framework
- Utilized io.freefair.aspectj.post-compile-weaving to get it done
- There are 3 methods to weave aspect into classes
  - Compile-Time Weaving
  - Post-Compile Weaving
  - Load-Time Weaving

I've failed...
- `Compile-Time Weaving` and `Load-Time Weaving`. I've tried but it didn't work as expected
  - I found working example w/ Post-Compile Weaving, but not for others
  - It seems to be related to Gradle configuration or something else, but I left it not done

# References
- https://www.baeldung.com/aspectj
- https://docs.freefair.io/gradle-plugins/8.0.1/reference/
- https://dev.to/pavankumar1234/aspectj-gradle-retry-java-method-on-exception-6b6
