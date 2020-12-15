package com.changemyminds.unittestnote.repository;

import com.changemyminds.unittestnote.domain.AppUser;

import java.util.Optional;

/**
 * Author: Changemyminds.
 * Date: 2020/12/14.
 * Description:
 */
public interface AppUserRepository {
    Optional<AppUser> findByUsername(String username);
    AppUser save(AppUser appUser);
}
