package by.grinuk.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/calculation")
    public String calculation (@RequestParam(value = "a") int a,
                               @RequestParam(value = "b") int b,
                               @RequestParam(value = "action") String action,Model model){

        double answer;
        switch (action) {
            case  "multiplication":
                answer=a*b;
                break;
            case "addition":
                answer=a+b;
                break;
            case ("subtraction"):
                answer=a-b;
                break;
            case ("division"):
                answer=a/(double)b;
                break;
            default:
                answer = 0;
                break;
        }

        model.addAttribute("message", answer);


        return "first/calculation";
    }
}
