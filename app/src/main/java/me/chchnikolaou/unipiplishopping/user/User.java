package me.chchnikolaou.unipiplishopping.user;

import java.util.List;

import me.chchnikolaou.unipiplishopping.order.Order;

public class User {


    private final int id;
    private final String firstName;
    private final String lastName;
    private final UserType type;
    private final String username;
    private final String email;
    private final String password;
    private final List<Order> orders;

    public User(int id, String firstName, String lastName, UserType type, String username, String email, String password,
                List<Order> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.username = username;
        this.email = email;
        this.password = password;
        this.orders = orders;
    }



}
