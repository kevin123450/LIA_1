package com.example.filhanterare;

import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FilhanterareApplication implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(FilhanterareApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            AppUser appUser1 = new AppUser("William", "william@hotmail.com" ,"pass", Set.of(ERole.ADMIN));
            AppUser appUser2 = new AppUser("Agda", "agda@hotmail.com" ,passwordEncoder.encode("pass"), Set.of(ERole.USER));
            appUserRepository.saveAll(List.of(
                    appUser1,
                    appUser2
            ));


        } catch (Exception e) {
            System.out.println("Det bidde fel");
        }
    }

}
