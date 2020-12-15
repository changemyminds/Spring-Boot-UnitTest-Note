package com.changemyminds.unittestnote.repository;

import com.changemyminds.unittestnote.domain.AppUser;
import com.changemyminds.unittestnote.exception.ConflictException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */
@Repository
public class AppUserRepositorySample implements AppUserRepository {
    private final List<AppUser> appUsers = new ArrayList<>();

    public AppUserRepositorySample() {
        appUsers.add(new AppUser(1L, "changemyminds", "1234", 27));
        appUsers.add(new AppUser(2L, "darren", "12345", 25));
        appUsers.add(new AppUser(3L, "kristin", "123456", 24));
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return appUsers.stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public AppUser save(AppUser appUser) {
        if (appUsers.stream().anyMatch(a -> a.getId().equals(appUser.getId()))) {
            throw new ConflictException("The id is conflict");
        }

        if (appUsers.stream().anyMatch(a -> a.getUsername().equals(appUser.getUsername()))) {
            throw new ConflictException("The username is conflict");
        }

        Long id = appUsers.get(appUsers.size() - 1).getId() + 1;
        appUser.setId(id);

        appUsers.add(appUser);
        return appUser;
    }
}
