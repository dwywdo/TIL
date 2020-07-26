# Spring - DI (Dependency Injection)
#a-part/todo
- - - -
* 필요한 의존성을 어떻게 받아올 것인가?
	* @Autowired / @Inject를 어디에 붙일까?
		* 생성자
			* 생성자에 붙이면 생성자를 사용해서 주입
			* Spring 4.3부터, 어떠한 클래스의 생성자가 하나 뿐이고, 그 생성자로 주입받는 레퍼런스 변수들이 Bean으로 등록되어 있다면 자동적으로 주입이 된다
			* 따라서 @Autowired  Annotation의 생략이 가능하다
		* 필드
			* 해당 필드 위에 @Autowired 붙일 수 있음 (필드를 통해 바로 주입받는 방식)
		* Setter
			* Spring IoC 컨테이너가 인스턴스를 만들고 나서, Setter를 통해서 주입 (생성자와 유사)
	* Bean으로 등록되지 않은 객체를 @Autowired로 주입받으려고 하면?
		* `No qualifying bean of type '~~' available`
	* 뭘 사용해야 할까?
		* 레퍼런스에서 권장하는 방법은 `생성자`를 통한 의존성 주입 방법
		* 왜? 필수적으로 사용해야 하는 레퍼런스 없이는 특정 인스턴스를 만들지 못하도록 강제할 수 있다. 필드나 Setter는 특정 포함하는 객체 없이도 인스턴스를 만들 수 있다
		* A -> B, B -> A 둘다 생성자 Injection을 사용해서는 만들 수 없다! 순환참조. 이런 경우에만 필드와 Setter 방식을 사용한다
		* 가능하면 Circular Dependency (순환 참조 의존성) 이 발생하지 않도록 설계를 하는 것이 좋다
* [토막 지식] 
	* CSS를 못찾는다거나 깨지는 등의 문제가 생기면?
		* maven -> package를 통해 재 빌드를 해준다
	* git stash의 의미? 날리는 데에 사용
		* 사실은 내 로컬 변경 사항을 잠시동안 담아두는 것
* 과제? OwnerController에 PetRepository 주입하기
	* 생성자를 사용할 때에는 Final을 가능하면 사용해주는 것이 좋다
	* 필드 인젝션 -> Final 키워드를 사용하지 못한다: 이미 인스턴스를 만들어야 하는 상황이기 때문
	* 마찬가지로 Setter를 쓸 때도 Final 키워드를 사용하지 못한다
