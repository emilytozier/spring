package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    //используем httpservletrequest когда нужно работать со всеми полями реквеста, не только с параметрами
    //если не передавать параметры, то она заполнит name, surname значением null
    public String helloPage(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("hello," + name + " "+ surname);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    //такая запись более удобна когда нужны только параметры
    //но здесь тогда при каждом гет запросе нужно передавать параметры в строке http://localhost:8080/first/goodbye?name=Tom&surname=Hardy
    //если их не передать возникнет ошибка, чтобы этого не было проставляем required =false
    public String goodbyePage(@RequestParam(value="name", required = false) String name, @RequestParam(value="surname", required = false) String surname, Model model){
       // System.out.println("goodbye," + name + " "+ surname);
        model.addAttribute("message", "Goodbye, " + name + " " + surname);
        return "first/goodbye";
    }
    //http://localhost:8080/ru_alishev_coursewebMVC3_war_exploded/first/calculator?a=3&b=5&action=division
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a, @RequestParam("b") int b,
                             @RequestParam("action") String action, Model model) {

        double result;

        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "division":
                result = a / (double) b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "addition":
                result = a + b;
                break;
            default:
                result = 0;
                break;
        }

        model.addAttribute("result", result);

        return "first/calculator";
    }
}
