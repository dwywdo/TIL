# Spring - Proxy Pattern
#tech/Spring
- - - -
* AOP 구현을 위한 프록시 패턴 (기존 코드 건드리지 않고 새 기능 추가하기)
	* Store의 입장에서는 계속해서 Payment라는 인터페이스를 사용한다. 즉,  Store / Cash 등의 클라이언트 코드는 전혀 변경되지 않았음
	* 이 코드 앞 뒤에 성능 측정하는 코드를 넣은 프록시 클래스(CashPerf)가 생김
	* 클라이언트가 프록시 코드를 사용하도록 테스트 코드에 작성
	* Cash 대신 CashPerf를 전달하면 성능 측정이 가능함
	* 새로운 코드를 추가하긴 했지만, 기존 코드를 건드리지는 않았다는 것이 핵심
* Spring AOP에서는 이런 일들이 자동으로 이루어진다
	* 복잡한 내부 메커니즘이 있긴 하다
		* CashPerf라는 프록시가 자동으로 빈으로써 등록될 때 만들어진다고 지금은 생각해두면 되겠다
		* 조금 더 디테일하게 적으면, 원래는 Cash라는 것이 Bean으로 등록되어야 하는데, 내가 만들고 싶은 Proxy가 자동으로 생겨서 Cash 대신에 CashPerf가 등록이 되고, 클라이언트가 Cash가 아닌 CashPerf를 대신 사용하게 되는 일이 스프링 내부에서는 발생한다
* @Transactional Annotation이 붙어있으면 그걸 감싸는 클래스 (인터페이스) 타입의 객체 타입의 프록시가 생성된다 (예: OwnerRepository)
	* JDBC에서의 Transaction 처리를 하려면 해당 부분 앞 뒤에 코드가 붙는다
		* connection(autocommit = false) 그 후에는 commit을 하거나, rollback하는 코드가 붙는다
		* 이러한 코드를 생략할 수 있게끔 해주는 것이 @Transactional
	* 이 처럼 앞 뒤로 코드를 넣어주는 방법이 Proxy 패턴을 사용하는 방법과 동일하다
	* 이러한 메커니즘들이 다 숨겨져 있다.. 비즈니스 로직에만 집중할 수 있도록 도움을 준다