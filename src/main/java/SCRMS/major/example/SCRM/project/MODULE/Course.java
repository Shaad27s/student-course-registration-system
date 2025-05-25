package SCRMS.major.example.SCRM.project.MODULE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Course {
   @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
   Long id;
   @Column(nullable = false)
   String courseName;
  @Column(nullable = false)
  String description;
  @Column(nullable = false)
  int credit;
  @Column(nullable = false)
  String schedule;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<Enrolllment> enrolllmentList;

    @ManyToOne
    @JoinColumn(name="Admin_id")
    private User admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
