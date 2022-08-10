package co.develhope.angelo.testController.controllers;

import co.develhope.angelo.testController.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public @ResponseBody String helloword(){
        return "Hello World";
    }

    @GetMapping("/user")
    public @ResponseBody User getUser(){
        return new User("Angelo","Zammataro");
    }
}
