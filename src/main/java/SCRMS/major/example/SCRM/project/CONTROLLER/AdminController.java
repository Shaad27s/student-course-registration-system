package SCRMS.major.example.SCRM.project.CONTROLLER;

import SCRMS.major.example.SCRM.project.MODULE.Course;
import SCRMS.major.example.SCRM.project.MODULE.Role;
import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.SERVICE.AdminService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<User> RegisterAdmin(@RequestBody User admin) {
        logger.info("Registering new admin: {}", admin.getEmail());
        User registeredAdmin = adminService.register(admin);
        logger.info("Admin registered successfully: {}", registeredAdmin.getEmail());
        return ResponseEntity.ok(registeredAdmin);
    }

   @PostMapping("/login") public  ResponseEntity<String> loginAdmin(@RequestBody User admin) {
       logger.info("Admin login attempt for email: {}", admin.getEmail());
       String result = adminService.login(admin);
        if (result == null) {
            logger.warn("Invalid login attempt for email: {}", admin.getEmail());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
       logger.info("Admin logged in successfully: {}", admin.getEmail());
       return ResponseEntity.ok(result);
    }

    // public ResponseEntity<Admin> login()
    @PostMapping("/createCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course CreatedCourse = adminService.createCourse(course);
        return ResponseEntity.ok(CreatedCourse);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updateCourse = adminService.updateCourse(id, course);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/DeleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        adminService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<?> FindStudent(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Current user: {}", auth.getName());
        logger.info("Authorities: {}", auth.getAuthorities());

//        System.out.println("Current user: " + auth.getName());
//        System.out.println("Authorities: " + auth.getAuthorities());

        User user = adminService.getStudent(id);
        if (user == null) {
            logger.warn("Student not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        logger.info("User  found for id {}: {}", id, user.getEmail());
        // System.out.println("i called");
        if (user.getRole() == Role.STUDENT) {
            logger.info("Returning student user details for id: {}", id);
            return ResponseEntity.ok(user);
        }
          else {
            logger.warn("User  with id {} is not a student but has role: {}", id, user.getRole());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a student");
            // return ResponseEntity.ok(null);
        }

    }
}
