package SCRMS.major.example.SCRM.project.SERVICE;

import SCRMS.major.example.SCRM.project.MODULE.*;
import SCRMS.major.example.SCRM.project.REPOSITORY.CourseRepo;
import SCRMS.major.example.SCRM.project.REPOSITORY.EnrollmentRepo;
import SCRMS.major.example.SCRM.project.REPOSITORY.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import SCRMS.major.example.SCRM.project.JWTconfigraution.jwtService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private jwtService jwtService;

    @Autowired
    private UserRepo studentRepo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    public  Enrolllment registerCourse(Long studentId, Long courseID) {

        User student = studentRepo.findById(studentId)
         .filter(u -> u.getRole() == Role.STUDENT)
                .orElseThrow(() -> new RuntimeException("User is not a student"));

        Course course =  courseRepo.findById(courseID)
                .orElseThrow(()-> new RuntimeException("course not found"));

        Enrolllment e = new Enrolllment();
        e.setCourse(course);
        e.setStudent(student);
        e.setEnrollmentDate(LocalDate.now());
        e.setStatus("Pending");
        return enrollmentRepo.save(e);

    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public  String login(User user) {

               System.out.println("Login attempt for: " + user.getEmail());

    //User dbUser = studentRepo.findByEmail(user.getEmail());
        Optional<User> optionalUser = studentRepo.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            System.out.println("User not found in DB.");
            return "Invalid email or password";
        }

        User dbUser = optionalUser.get();

//    if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
//        System.out.println("Password mismatch.");
//        return "Invalid email or password";
//    }

        Authentication authenticate =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authenticate.isAuthenticated()){
            return  jwtService.getToken(user.getEmail());
        }
        else
            return "fail";

//      return   studentRepo. findByEmailAndPassword( email, password)
//              .filter(u -> u.getRole() == Role.ROLE_STUDENT)
//              .orElseThrow(() -> new RuntimeException("User is not a student"));

              //findAll().stream().filter(s->s.getEmail().equals((email))
             // && s.getPassword().equals((password))).findFirst().orElse(null);
    }

    public User register(User student) {

        if (studentRepo.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        student.setPassword(encoder.encode(student.getPassword()));
        student.setRole(Role.STUDENT);
        return studentRepo.save(student);


//        if (studentRepo.existsByEmail(student.getEmail())){
//            throw new RuntimeException("Email already exists");
//        }
//         return  studentRepo.save(student);

    }
}
