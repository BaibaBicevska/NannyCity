package com.nannycity.entities;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ParentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "User's name cannot be empty.")
    private String userName;
    private String userFullName;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;

    private String location;
    private String address;


    private String description;
    private boolean status;

    private String language;

    private String hours;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


    public ParentUser(String userName, String userFullName, String email, String password, String phone, String location, String address, String description, String hours, String language) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.location = location;
        this.address = address;
        this.description = description;
        this.hours = hours;
        this.language = language;

    }
}