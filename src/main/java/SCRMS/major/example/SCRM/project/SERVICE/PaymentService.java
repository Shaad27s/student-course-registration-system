package SCRMS.major.example.SCRM.project.SERVICE;

import SCRMS.major.example.SCRM.project.MODULE.Payment;
import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.REPOSITORY.PaymentRepo;
import SCRMS.major.example.SCRM.project.REPOSITORY.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private UserRepo userRepo;


    public Payment makePayment(Long studentId, String paymentMethod, double amount) {
        User student = userRepo.findById(studentId)
                .orElseThrow(()-> new RuntimeException("Student not found"));

        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setStudent(student);
        return paymentRepo.save(payment);
    }

    public Payment getById(Long id) {
        return paymentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Payment not found"));
    }


}
