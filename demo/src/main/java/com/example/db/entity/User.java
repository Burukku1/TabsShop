package com.example.db.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique=true, nullable = false)
    private Long userId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "user_tabs_junction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tab_id")
    )
    private Set<Tabs> tabs = new HashSet<>();

    public Set<Tabs> getTabs() {
        return tabs;
    }

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User u = (User)o;
        return Objects.equals(userId, u.userId) && Objects.equals(login, u.login) && Objects.equals(password, u.password) && Objects.equals(email, u.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}