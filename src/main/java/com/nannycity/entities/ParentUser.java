package com.nannycity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ParentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private String userSecondName;
    @Column(unique = true)
    private String email;
    private String password;

    private String location;
    private String address;
    private String phone;
    private boolean status;

    private String language;

    private String hours;
    private String description;


    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public ParentUser(String userName, String userSecondName, String email, String password, String phone, String location, String address, String hours, String language, String description) {

        this.userName = userName;
        this.userSecondName = userSecondName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.location = location;
        this.address = address;
        this.hours = hours;
        this.language = language;
        this.description = description;
    }
}