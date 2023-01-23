package com.example.filhanterare.controller;

import com.example.filhanterare.dto.AppUserResponseDTO;
import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.service.AppUsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class AppUsersController {

    private final AppUsersService appUsersService;

    public AppUsersController(AppUsersService appUsersService) {
        this.appUsersService = appUsersService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AppUserResponseDTO> getAllUsers() {
        return appUsersService.getAllUsers()
                .stream()
                .map(AppUser::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AppUserResponseDTO getUser(@PathVariable("id") long userId) {
        return appUsersService.findUserById(userId).toResponseDTO();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable long id){
        appUsersService.deleteUserById(id);
    }

}
