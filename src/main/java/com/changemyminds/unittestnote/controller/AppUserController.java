package com.changemyminds.unittestnote.controller;

import com.changemyminds.unittestnote.service.AppUserService;
import com.changemyminds.unittestnote.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */
@RequestMapping("/api/user")
@RestController
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    public AppUser findUsernameByPath(@PathVariable String username) {
        return appUserService.findByUsername(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AppUser createAppUser(@RequestBody AppUser appUser) {
        return appUserService.createAppUser(appUser);
    }
}
