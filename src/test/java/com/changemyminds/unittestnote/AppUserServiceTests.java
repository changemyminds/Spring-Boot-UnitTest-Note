package com.changemyminds.unittestnote;

import com.changemyminds.unittestnote.domain.AppUser;
import com.changemyminds.unittestnote.exception.NotFoundException;
import com.changemyminds.unittestnote.repository.AppUserRepository;
import com.changemyminds.unittestnote.service.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Author: Changemyminds.
 * Date: 2020/12/14.
 * Description: @InjectMocks使用在Class上而非Interface
 */
public class AppUserServiceTests {
    // 會將@Mock物件注入，這邊會將userRepository注入
    @InjectMocks
    private AppUserServiceImpl appUserService;
    @Mock
    private AppUserRepository appUserRepository;

    @BeforeEach
    void setup() {
        // 如果沒使用initMocks的話，會造成appUserService為空(null)物件
        // 會將@Mock、@InjectMocks等等物件進行初始話
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("尋找存在的使用者")
    public void findByUsernameExist() {
        AppUser expected = new AppUser(1L, "sinyi", "773399", 20);
        when(appUserRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.of(expected));

        String username = "changemyminds";
        AppUser result = appUserService.findByUsername(username);
        assertAll(
                () -> assertEquals(expected.getId(), result.getId()),
                () -> assertEquals(expected.getUsername(), result.getUsername()),
                () -> assertEquals(expected.getPassword(), result.getPassword()),
                () -> assertEquals(expected.getAge(), result.getAge())
        );

        verify(appUserRepository).findByUsername(username);
    }

    @Test
    @DisplayName("尋找不存在的使用者")
    public void findByUsernameNotExist() {
        when(appUserRepository.findByUsername(ArgumentMatchers.anyString())).thenThrow(new NotFoundException());
        assertThrows(NotFoundException.class, () -> appUserService.findByUsername("changemyminds"));
    }

    @Test
    @DisplayName("新增的使用者存在")
    public void createAppUserAndExist() {
        AppUser expected = new AppUser(1L, "changemyminds", "1234", 27);
        when(appUserRepository.save(ArgumentMatchers.any())).thenReturn(expected);
        AppUser result = appUserService.createAppUser(new AppUser(1L, "changemyminds", "1234", 27));

        assertAll(
                () -> assertEquals(expected.getId(), result.getId()),
                () -> assertEquals(expected.getUsername(), result.getUsername()),
                () -> assertEquals(expected.getPassword(), result.getPassword()),
                () -> assertEquals(expected.getAge(), result.getAge())
        );
    }

    @Test
    @DisplayName("新增的使用者不存在")
    public void createAppUserAndNotExist() {
        AppUser expected = new AppUser(1L, "changemyminds", "1234", 27);
        when(appUserRepository.save(ArgumentMatchers.any())).thenThrow(new NotFoundException());
        assertThrows(NotFoundException.class, () -> appUserService.createAppUser(expected));
    }
}
