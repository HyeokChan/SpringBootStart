package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		/*스프링부트 애플리케이션 실행
		* 톰캣 웹서버 내장*/
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
