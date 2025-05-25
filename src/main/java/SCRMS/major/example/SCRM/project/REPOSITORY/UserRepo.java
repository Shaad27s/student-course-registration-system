package SCRMS.major.example.SCRM.project.REPOSITORY;

import SCRMS.major.example.SCRM.project.MODULE.Role;
import SCRMS.major.example.SCRM.project.MODULE.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);



    List<User> findByRole(Role role);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
//    @Query("SELECT u FROM  User u WHERE u.name = :name ")
//    User findByName(@Param("name") String name);
//
//    @Query("SELECT u FROM  User u WHERE u.email = :email ")
//    User findByEmail(@Param("email") String email);

}
