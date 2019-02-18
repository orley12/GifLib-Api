//package com.teamtreehouse.user;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.teamtreehouse.core.BaseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.persistence.Entity;
//
//@Entity
//public class User extends BaseEntity {
//
//    /**
//     * for encoding the password string
//     */
//    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
//
//    private String firstName;
//
//    private String lastName;
//
//    private String username;
//
//    /**
//     * @JsonIgnore this is to indicate that when this entities end point
//     * is access the password field shouldn't me include/shown
//     */
//    @JsonIgnore
//    private String password;
//
//    /**
//     * roles is defined to specify the kind of action certain user can perform eg Admin or ordinary user roles.
//     * @JsonIgnore this is to indicate that when this entities end point
//     * is access the roles field shouldn't me include/shown
//     */
//    @JsonIgnore
//    private String[] roles;
//
//    public User(String firstName, String lastName, String username, String password, String[] roles) {
//        super();
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }
//}
