package com.changemyminds.unittestnote.service;

import com.changemyminds.unittestnote.domain.AppUser;

/**
 * Author: Changemyminds.
 * Date: 2020/12/14.
 * Description:
 */
public interface AppUserService {
    AppUser findByUsername(String username);
    AppUser createAppUser(AppUser appUser);
}
