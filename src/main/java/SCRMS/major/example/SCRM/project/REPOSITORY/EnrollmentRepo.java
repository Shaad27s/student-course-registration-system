package SCRMS.major.example.SCRM.project.REPOSITORY;

import SCRMS.major.example.SCRM.project.MODULE.Enrolllment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrolllment,Long>{
}
