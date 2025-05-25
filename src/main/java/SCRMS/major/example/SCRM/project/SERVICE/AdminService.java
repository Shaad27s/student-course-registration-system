package SCRMS.major.example.SCRM.project.SERVICE;

import SCRMS.major.example.SCRM.project.MODULE.Course;
import SCRMS.major.example.SCRM.project.MODULE.Role;
import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.REPOSITORY.CourseRepo;
import SCRMS.major.example.SCRM.project.REPOSITORY.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import SCRMS.major.example.SCRM.project.JWTconfigraution.jwtService;

@Service
public class AdminService {
    @Autowired
    private CourseRepo courseRepo;

   @Autowired
   private UserRepo userRepo;
  @Autowired
  private jwtService jwtService;

   @Autowired
    AuthenticationManager authenticationManager;



    public  Course updateCourse(Long id, Course course) {
        Course existingCourse= courseRepo.findById(id).orElseThrow();
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setCredit(course.getCredit());
        existingCourse.setSchedule(course.getSchedule());
        return  courseRepo.save(existingCourse);

    }

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);

    }

    public User getStudent(Long id) {
        return userRepo.findById(id).orElseThrow();
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User admin) {

        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);

        return userRepo.save(admin);
//        if (userRepo.existsByEmail(admin.getEmail())){
//            throw new RuntimeException("Email already exists");
//
//    }

    }

    public String login(User admin) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getEmail(),admin.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.getToken(admin.getEmail());
        }
        else
            return "Login failed";
    }
}
