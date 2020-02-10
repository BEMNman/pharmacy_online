package com.epam.finalproject.pharmacy.entity;

public class User implements Identifable{

    public static final String NAME_TABLE_IN_DB = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    private final Long id;
    private final String name;
    private final String login;
    private final String password;
    private final UserRole role;

    public User(String name, String login, String password, UserRole role) {
        this.id = null;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String name, String login, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public static User newPatient(String name, String login, String password){
        return new User(name, login, password, UserRole.PATIENT);
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public Long getId() {
        return id;
    }
}
