package SCRMS.major.example.SCRM.project.CONTROLLER;

import SCRMS.major.example.SCRM.project.MODULE.Course;
import SCRMS.major.example.SCRM.project.REPOSITORY.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Courses")
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;

   @GetMapping
   public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> courses = courseRepo.findAll();
        if(courses.isEmpty()){
            return ResponseEntity.noContent().build();
        }
         return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Course> CourseById(@PathVariable Long id){
       return courseRepo.findById(id)
               .map(course -> ResponseEntity.ok(course))
               .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
