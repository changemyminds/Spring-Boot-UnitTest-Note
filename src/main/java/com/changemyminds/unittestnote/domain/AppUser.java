package com.changemyminds.unittestnote.domain;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */
public class AppUser {
    private Long id;
    private String username; // 使用者帳號
    private String password; // 使用者密碼
    private Integer age;     // 使用者年齡

    public AppUser() {
    }

    public AppUser(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public AppUser(Long id, String username, String password, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
