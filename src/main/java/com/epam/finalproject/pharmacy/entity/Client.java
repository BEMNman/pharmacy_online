package com.epam.finalproject.pharmacy.entity;

public class Client implements Identifable{
    private final long id;
    private final String name;
    private final String login;
    private final String password;
    private final RoleType roleType;

    public Client(long id, String name, String login, String password, RoleType roleType) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.roleType = roleType;
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

    public RoleType getRoleType() {
        return roleType;
    }

    @Override
    public long getId() {
        return 0;
    }
}
