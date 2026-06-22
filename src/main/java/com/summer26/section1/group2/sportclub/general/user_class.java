package com.summer26.section1.group2.sportclub.general;

import java.util.ArrayList;
import java.util.List;

public class user_class {
    private String username;
    private String password;
    private String role;

    public static List<user_class> userlist = new ArrayList<>();

    public user_class(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String get_user_name() {
        return username;
    }

    public void set_user_name(String username) {
        this.username = username;
    }

    public String get_password() {
        return password;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public String get_role() {
        return role;
    }

    public void set_role(String role) {
        this.role = role;
    }

    public String to_string_output() {
        return "User{userName='" + username + "', role='" + role + "'}";
    }

}
