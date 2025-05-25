package SCRMS.major.example.SCRM.project.CONTROLLER;

import SCRMS.major.example.SCRM.project.DTOs.PaymentDTO;
import SCRMS.major.example.SCRM.project.MODULE.Payment;
import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.REPOSITORY.UserRepo;
import SCRMS.major.example.SCRM.project.SERVICE.PaymentService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Student/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/make")
    public ResponseEntity<Payment> makePayment(@RequestBody Map<String, Object> request) {
        Long studentId = ((Number) request.get("studentId")).longValue();
        String paymentMethod = (String) request.get("paymentMethod");
        double amount = ((Number) request.get("amount")).doubleValue();

        Payment payment = paymentService.makePayment(studentId, paymentMethod, amount);


        return ResponseEntity.ok(payment);
    }
    @GetMapping("/get/{id}")
    public  ResponseEntity<Payment>getPaymentById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getById(id));
    }

}
