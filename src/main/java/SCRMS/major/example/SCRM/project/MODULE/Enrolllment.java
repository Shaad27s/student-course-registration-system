package SCRMS.major.example.SCRM.project.MODULE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Enrolllment {
   @Id
   @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
   int id;
   @Column
    LocalDate enrollmentDate;

   @Column
    String status;

   @ManyToOne
   @JoinColumn(name="Course_id")
   private Course course;

   @ManyToOne
   @JoinColumn(name="Student_id")
   private  User student;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public LocalDate getEnrollmentDate() {
      return enrollmentDate;
   }

   public void setEnrollmentDate(LocalDate enrollmentDate) {
      this.enrollmentDate = enrollmentDate;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Course getCourse() {
      return course;
   }

   public void setCourse(Course course) {
      this.course = course;
   }

   public User getStudent() {
      return student;
   }

   public void setStudent(User student) {
      this.student = student;
   }
}
