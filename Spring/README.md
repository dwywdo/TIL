### Spring Framework vs. Spring Boot

---

- Java Framework (Platform) that handles all the kinds of infrastructre so you can focus on only business logic.
- Free, Open Source Created in 2003, by Rod Johnson
- Supports wide range of applications
  - Application Servers such as Tomcat
  - Single JAR (embedded server)
  - Standalone Applications

##### Spring Modules

---

- Spring Core
  - Dependency Injection Container that gives multiple ways to define what are called 'Beans'
  - IOC Container
- Testing
- Data Access (DAO Support)
- Web Servlet (Spring MVC) - Websocket, Sock.js, etc.
- Web Reactive (Spring WebFlux)
- Integration (Remoting, JMX, Scheduling, Caching, etc.)
- Languages (Java, Kotlin, Groovy, etc.)

> Almost 1 in 2 developers base their code on Spring

##### Spring Boot

---

- Complex Spring Application, Configuring, etc. => Complicated, Horrible
  - Spring Boot Starters (Dependency made up of dependencies)
    - Web Application -> Tomcat, Validation, Spring Web, Spring MVC, etc.
  - Spring Boot Autoconfiguration
    - Conditions on it (Property based, Existing Class based, etc.)
  - Developer Friendly
  - Production Ready
- You will never create a spring project without boot again
