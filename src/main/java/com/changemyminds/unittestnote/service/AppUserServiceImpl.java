package com.changemyminds.unittestnote.service;

import com.changemyminds.unittestnote.domain.AppUser;
import com.changemyminds.unittestnote.exception.NotFoundException;
import com.changemyminds.unittestnote.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
