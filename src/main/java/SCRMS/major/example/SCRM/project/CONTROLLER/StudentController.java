package SCRMS.major.example.SCRM.project.CONTROLLER;

import SCRMS.major.example.SCRM.project.MODULE.Course;
import SCRMS.major.example.SCRM.project.MODULE.Enrolllment;
import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.SERVICE.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

   @PostMapping("/register")
   public ResponseEntity<User> registerStudent(@RequestBody User student ){
        User registeredStudent = studentService.register(student);
        return ResponseEntity.ok(registeredStudent);
    }

   @PostMapping("/login")
   public ResponseEntity<String> loginStudent(@RequestBody User user) {
       String result =studentService.login(user);
       if (result == null) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");

        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/registerCourse/{courseID}")
    public ResponseEntity<Enrolllment> registerCourse(@RequestParam Long studentId,
                                                      @PathVariable Long courseID){

       return ResponseEntity.ok(studentService.registerCourse(studentId,courseID));
    }
}
