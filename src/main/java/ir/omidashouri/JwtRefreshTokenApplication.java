package ir.omidashouri;

import ir.omidashouri.domain.RoleEntity;
import ir.omidashouri.domain.UserEntity;
import ir.omidashouri.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtRefreshTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtRefreshTokenApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    after first run comment @Bean
//    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
          userService.saveRole(new RoleEntity(null, "ROLE_USER"));
          userService.saveRole(new RoleEntity(null, "ROLE_MANAGER"));
          userService.saveRole(new RoleEntity(null, "ROLE_ADMIN"));
          userService.saveRole(new RoleEntity(null, "ROLE_SUPER_ADMIN"));

          userService.saveUser(new UserEntity(null, "omid ashouri","omid","1234", new ArrayList<>()));
          userService.saveUser(new UserEntity(null, "omid ashouri1","omid1","1234", new ArrayList<>()));
          userService.saveUser(new UserEntity(null, "omid ashouri2","omid2","1234", new ArrayList<>()));
          userService.saveUser(new UserEntity(null, "omid ashouri3","omid3","1234", new ArrayList<>()));

          userService.addRoleToUser("omid1", "ROLE_USER");
          userService.addRoleToUser("omid2", "ROLE_MANAGER");
          userService.addRoleToUser("omid3", "ROLE_ADMIN");
          userService.addRoleToUser("omid", "ROLE_ADMIN");
          userService.addRoleToUser("omid", "ROLE_SUPER_USER");
          userService.addRoleToUser("omid", "ROLE_USER");
        };
    }

}
