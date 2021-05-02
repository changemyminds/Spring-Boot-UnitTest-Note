package com.changemyminds.unittestnote.controller;

import com.changemyminds.unittestnote.domain.AppUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */
public class AppUserControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("找尋存在的使用者資訊")
    public void findByUsernameExist() throws Exception {
        mockMvc.perform(get("/api/user/{username}", "changemyminds"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("changemyminds"))
                .andExpect(jsonPath("$.password").value("1234"))
                .andExpect(jsonPath("$.age").value(27));
    }

    @Test
    @DisplayName("找尋存在的使用者資訊，另外一種寫法")
    public void findByUsernameExist2() throws Exception {
        ResultActions resultActions = performGet("/api/user/changemyminds")
                .andDo(PRINT)
                .andExpect(STATUS_OK);

        AppUser appUser = fromResult(resultActions, AppUser.class);
        assertThat(appUser.getId()).isEqualTo(1L);
        assertThat(appUser.getUsername()).isEqualTo("changemyminds");
        assertThat(appUser.getPassword()).isEqualTo("1234");
        assertThat(appUser.getAge()).isEqualTo(27);
    }



    @Test
    @DisplayName("找尋不存在的使用者資訊")
    public void findByUsernameNotExist() throws Exception {
        mockMvc.perform(get("/api/user/{username}", "hello"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("創建使用者")
    public void createAppUser() throws Exception {
        AppUser appUser = new AppUser("海倫", "7788", 8);
        String requestJson = toJson(appUser);

        getCreateAppUserPerform(requestJson)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.username").value(appUser.getUsername()))
                .andExpect(jsonPath("$.password").value(appUser.getPassword()))
                .andExpect(jsonPath("$.age").value(appUser.getAge()));
    }

    @Test
    @DisplayName("創建已存在的使用者，使用者名稱相同")
    public void createAppUserExistByUsername() throws Exception {
        AppUser appUser = new AppUser("darren", "1223", 29);
        String requestJson = toJson(appUser);

        getCreateAppUserPerform(requestJson)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("創建已存在的使用者，使用者id相同")
    public void createAppUserExistById() throws Exception {
        AppUser appUser = new AppUser(1L, "darren", "1223", 29);
        String requestJson = toJson(appUser);

        getCreateAppUserPerform(requestJson)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private ResultActions getCreateAppUserPerform(String requestJson) throws Exception {
        return mockMvc.perform(post("/api/user").headers(createHttpHeaders()).content(requestJson));
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }
}
