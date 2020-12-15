package com.changemyminds.unittestnote;

import com.changemyminds.unittestnote.domain.AppUser;
import com.changemyminds.unittestnote.exception.ConflictException;
import com.changemyminds.unittestnote.repository.AppUserRepositorySample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */
public class AppUserRepositoryTest {
    private final AppUserRepositorySample userRepositorySample = new AppUserRepositorySample();

    @Test
    @DisplayName("儲存的使用者已存在，因為使用者id相同")
    public void saveSameIdUser() {
        AppUser appUser = new AppUser(1L, "amy", "1234", 27);
        assertThrows(ConflictException.class, () -> userRepositorySample.save(appUser));
    }

    @Test
    @DisplayName("儲存的使用者已存在，因為使用者名稱相同")
    public void saveSameUsername() {
        AppUser appUser = new AppUser(99L, "changemyminds", "1234", 27);
        assertThrows(ConflictException.class, () -> userRepositorySample.save(appUser));
    }

    @Test
    @DisplayName("儲存的使用者不存在")
    public void saveUserNotExist() {
        AppUser expected = new AppUser(0L, "frank", "1234", 27);
        AppUser result = userRepositorySample.save(expected);

        assertAll(
                () -> assertEquals(4L, result.getId()),
                () -> assertEquals(expected.getUsername(), result.getUsername()),
                () -> assertEquals(expected.getPassword(), result.getPassword()),
                () -> assertEquals(expected.getAge(), result.getAge())
        );
    }
}
