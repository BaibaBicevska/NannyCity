package com.nannycity.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NannyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    //@NotEmpty(message = "User's name cannot be empty.")
    //@Size(min = 5, max = 250)
    private String userName;
    private String userFullName;
    @Column(unique = true)
    private String email;
    private String password;
    private String birthDay;
    private String phone;
    private String location;
    private String address;
    private String profilePicture;
    private String hours;
    private String skills;
    private String education;
    private String experience;
    private String language;
    private String description;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public NannyUser(String userName, String userFullName, String email, String password, String birthDay, String phone, String location, String address, String hours, String skills, String education, String experience, String language, String description) {

        this.userName = userName;
        this.userFullName = userFullName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.phone = phone;
        this.location = location;
        this.address = address;
        this.hours = hours;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.language = language;
        this.description = description;
    }
}
