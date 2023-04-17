package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /*get방식으로 hello 넘어왔을 때 실행*/
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        /* resources/templates/hello.html 실행
        * 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.*/
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*@ResponseBody : http body 부분에 데이터를 직접 넣어주겠다. StringConverter 작동*/
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    /*API 방식 : 객체를 JSON 형태로 반환. JsonConverter 작동*/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    /*static : 클래스 내부에서 객체를 만들기 위한 클래스 생성*/
    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
