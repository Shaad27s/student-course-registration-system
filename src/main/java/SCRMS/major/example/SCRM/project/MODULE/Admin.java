package SCRMS.major.example.SCRM.project.MODULE;

//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class Admin {
//    @Id
//            @GeneratedValue(strategy = GenerationType.AUTO)
//    int id;
//    @Column(nullable = false)
//    String name;
//    @Column(nullable = false)
//    String email;
//    @Column(nullable = false)
//    String password;
//
//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
//    private List<Course> course;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
