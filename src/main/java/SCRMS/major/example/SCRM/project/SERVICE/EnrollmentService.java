package SCRMS.major.example.SCRM.project.SERVICE;

import SCRMS.major.example.SCRM.project.MODULE.Course;
import SCRMS.major.example.SCRM.project.MODULE.Enrolllment;
import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.REPOSITORY.CourseRepo;
import SCRMS.major.example.SCRM.project.REPOSITORY.EnrollmentRepo;
import SCRMS.major.example.SCRM.project.REPOSITORY.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EnrollmentService {
    @Autowired
    private UserRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;
   @Autowired
   private EnrollmentRepo enrollmentRepo;
    public Enrolllment Enrolled(Long studentId, Long courseId) {

        User student = studentRepo.findById(studentId).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        Enrolllment enr = new Enrolllment();

        enr.setStudent(student);
        enr.setCourse(course);
        enr.setEnrollmentDate(LocalDate.now());
        enr.setStatus("pending");
        return  enrollmentRepo.save(enr);
    }

    public Enrolllment getEnrollment(Long id) {
        return enrollmentRepo.findById(id).orElseThrow();
    }

    public Object updateStatus(Long id, String status) {

        Enrolllment enrollment = enrollmentRepo.findById(id).orElseThrow();
        enrollment.setStatus(status.toString());
        return enrollmentRepo.save(enrollment);
    }


}




