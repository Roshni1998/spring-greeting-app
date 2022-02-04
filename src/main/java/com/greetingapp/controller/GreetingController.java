package com.greetingapp.controller;

import com.greetingapp.entity.Greeting;
import com.greetingapp.entity.User;
import com.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingService greetingService;

    @GetMapping(value = {"", "/"})
    public Greeting greeting(@RequestParam(value = "fname", defaultValue = "World") String fname,
                             @RequestParam(value = "lname", defaultValue = "") String lname){
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        return greetingService.addGreeting(user);
    }

    @GetMapping(value = {"/{id}"})
    public Greeting greetingById(@PathVariable("id") Long id){
        return greetingService.getGreetingById(id);
    }
}
