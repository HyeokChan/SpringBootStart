# Springboot Start

## 개발 / 학습 환경
- Project : Gradle Project
- Spring Boot : 2.3
- Language : Java
- Pajaging : Jar
- Java : 11

## 라이브러리
### 스프링 부트 라이브러리
- spring-boot-starter-tomcat : 톰캣(웹서버)
- spring-webmvc : 스프링 웹 MVC
- spring-boot-starter-thymeleaf : 타임리프 템플릿 엔진(View)
- spring-boot-starter : 스프링부트, 스프링코어, 로깅
### 테스트 라이브러리
- spring-boot-starter-test

## 스프링 웹 개발 기초
### 정적 컨텐츠
1. 웹 브라우저에서 GET방식으로 URL을 입력 받았을 때 : 
localhost:8080/hello-static.html
2. 스프링 컨테이너에서 관련 컨트롤러를 찾지 못하면 
(model에 데이터를 담을 수 없다.)
4. resources/static/hello-static.html 파일을 찾아 호출한다.

### MVC / 템플릿 엔진
1. 웹 브라우저에서 GET방식으로 URL을 입력받았을 때 :
localhost:8080/hello-mvc
2. 스프링컨테이너에서 hello-mvc로 매핑된 메소드를 실행시킨다.
(return : hello-template)
3. viewResolver를 통해 template/hello-template.html을 호출한다.

### API
#### @ResposeBody 어노테이션을 사용하여 viewResolver를 사용하지 않는다.
#### 대신에 HTTP의 BODY에 데이터를 직접 반환한다.
1. 웹 브라우저에서 GET방식으로 URL을 입력받았을 때 :
localhost:8080/hello-api
2. 스프링컨테이너에서 hello-api로 매핑된 메소드를 실행시킨다.
(return : hello(객체))
3. viewResolver 대신에 HttpMessageConverter가 동작하여 처리한다.
	- 문자일 경우 : StringConverter
	- 객체인 경우 : JsonConverter를 통해 Json 형태로 반환

## 회원 관리 예제
### 비즈니스 요구사항
- 데이터 : 회원ID, 이름
- 기능 : 회원 등록, 조회
- 조건 : 아직 데이터 저장소가 선정되지 않음

### 웹 애플리케이션 계층 구조
- 컨트롤러 : 웹 MVC의 컨트롤러 역할
- 서비스 : 핵심 비즈니스 로직 구현
- 리포지토리 : 데이터베이스 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인 : 비즈니스 도메인 객체

### 클래스 의존 관계
서비스가 리포지토리 인터페이스에 의존적이고, 데이터 저장소가 아직 선정되지 않았으므로 리포지토리 인터페이스의 구현체를 통해 데이터 저장소 변경에 용이하다.

### 테스트 케이스 작성
- @AfterEach 어노테이션을 사용한 메소드는 테스트하는 각 메소드 실행 후 자동으로 호출된다. 테스트를 통해 생성된 데이터를 삭제하기 용이하다.
- @BeforeEach 어노테이션을 사용한 메소드는 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고 의존관계도 새로 맺어준다.
- 테스트는 각각 독립적으로 실행되어야 하며, 테스트 순서에 의존관계는 없어야 한다.

## 스프링 빈과 의존관계
### 컴포넌트 스캔과 자동 의존관계 설정
@Autowired 어노테이션을 통해 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.(DI)
 - 생성자 주입
	<pre><code>
	@Autowired
	public MadExample(HelloService helloService) {
	    this.helloService = helloService;
	}
	</code></pre>
	
 - 필드 주입
	<pre><code>
	@Autowired
	private HelloService helloService;
	</code></pre>

- 수정자 주입
	<pre><code>
	@Autowired
	public void setHelloService(HelloService helloService) {
	    this.helloService = helloService;
	}
	</code></pre>

3가지 주입 방법 중 생성자 주입 방법을 권장하고 있다.
	
### 자바 코드로 직접 스프링 빈 등록
@Configuration 어노테이션을 사용한 클래스에 @Bean태그를 사용하여 스프링 빈을 등록하고 의존관계를 맺을 수 있다.

의존 관계를 수정하는 것이 간편하기 때문에 향후 의존 관계를 변경 할 일이 있다면 컴포넌트 스캔 방식보다 직접 스프링 빈을 등록하는 것이 편할 수 있다.

## 스프링 DB 접근 기술
### 순수 Jdbc
Connection, PreParedStatement, ResultSet을 이용하여 db연결과 쿼리 작성, 결과 반환을 위해 작성해야 할 코드가 많으며 각 기능을 위한 중복 코드가 사용된다.

### 스프링 Jdbc Template
스프링 Jdbc Template과 MyBatis 같은 라이브러리를 통해 순수 Jdbc에 필요한 반복 코드를 대부분 제거해준다.
하지만 sql쿼리는 직접 작성해야한다.

### JPA
기존의 반복코드를 없애고 기본적인 sql쿼리를 JPA가 직접 만들어서 실행해준다.
JPA를 사용하면, sql과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임의 전환을 할 수 있다.

### 스프링 데이터 JPA
JPA를 더 편하게 사용할 수 있으며, 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다. 따라서 개발자는 핵심 비즈니스 로직을 개발하는데 집중할 수 있다.


## AOP
- 회원가입, 회원조회 등 핵심관심사항과 시간을 측정하는 공통관심사항을 분리한다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들 수 있다.
- 핵심관심사항을 깔끔하게 유지할 수 있다.
- 변경이 필요하면 aop 로직만 변경할 수 있다.
- 원하는 적용 대상을 선택할 수 있다.

AOP 적용 시 프록시 코드가 생성되어 동작한다.
--