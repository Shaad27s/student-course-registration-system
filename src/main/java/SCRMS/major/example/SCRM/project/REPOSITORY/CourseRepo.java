package SCRMS.major.example.SCRM.project.REPOSITORY;

import SCRMS.major.example.SCRM.project.MODULE.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
}
