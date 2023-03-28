package com.example.filhanterare.service;

import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.repo.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUsersService {

    private final AppUserRepository appUserRepository;

    public AppUsersService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser findUserById(long userId) {
        return appUserRepository.findAppUserById(userId).orElseThrow();
    }

    public void deleteUserById(long id) {
        appUserRepository.deleteById(id);
    }

}
