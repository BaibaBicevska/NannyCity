package com.nannycity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Chat {
    @Id
    @GeneratedValue
    private Long id;

    String message;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="USERID", referencedColumnName="id")
    private NannyUser nannyUser;

    @CreationTimestamp
    Timestamp createdAt;
}
