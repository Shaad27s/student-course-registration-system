package SCRMS.major.example.SCRM.project.CONTROLLER;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
   @GetMapping("/demo")
   public String GetDemo(){
        return "Hello World";
    }
}
