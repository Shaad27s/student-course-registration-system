package SCRMS.major.example.SCRM.project.REPOSITORY;

import SCRMS.major.example.SCRM.project.MODULE.Payment;
import SCRMS.major.example.SCRM.project.MODULE.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
    List<Payment> findByStudent(User student);
}
