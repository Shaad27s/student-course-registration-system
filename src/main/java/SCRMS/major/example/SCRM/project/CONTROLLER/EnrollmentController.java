package SCRMS.major.example.SCRM.project.CONTROLLER;

import SCRMS.major.example.SCRM.project.MODULE.Enrolllment;
import SCRMS.major.example.SCRM.project.SERVICE.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/Enrollment")
public class EnrollmentController {

    private EnrollmentService enroll;

   @PostMapping("/Enroll")
   public ResponseEntity<Enrolllment> Enrolled(@RequestBody Map<String ,Long> body){
        Long studentId = body.get("studentId");
        Long courseId = body.get("courseId");
        return ResponseEntity.ok(enroll.Enrolled(studentId,courseId));
   }

   @PostMapping("/FindEnroll/{id}")
   public  ResponseEntity<Enrolllment> getEnrollment(@PathVariable Long id){
       return  ResponseEntity.ok(enroll.getEnrollment(id));
   }
   @PostMapping("/updateStatus/{id}")
   public  ResponseEntity<?>updateStatus(@PathVariable Long id,
                                         @RequestBody Map<String,Long> status){
       return ResponseEntity.ok(enroll.updateStatus(id,status.get("status").toString()));
   }
}
