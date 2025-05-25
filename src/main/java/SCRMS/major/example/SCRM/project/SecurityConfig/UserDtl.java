package SCRMS.major.example.SCRM.project.SecurityConfig;

import SCRMS.major.example.SCRM.project.MODULE.User;
import SCRMS.major.example.SCRM.project.REPOSITORY.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDtl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> {
            System.out.println("User not found: " + email);
            return new UsernameNotFoundException("User not found");
        });
        System.out.println("User found: " + user.getEmail());
        return new NewUserConfig(user);
    }

}
