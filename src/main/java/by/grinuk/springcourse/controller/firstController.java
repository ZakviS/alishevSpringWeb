package by.grinuk.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class firstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname",required = false) String surname){

        System.out.println("Hello " + name + " " + surname);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }
}
