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
public class FAQ {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private String person;

//    @ManyToOne
//    @PrimaryKeyJoinColumn(name="USERID", referencedColumnName="id")
//    private ParentUser parentuser;

    @CreationTimestamp
    Timestamp createdAt;


//    public FAQ(String question) {
//        this.question = question;
//    }
//}
}