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
    @CreationTimestamp
    Timestamp createdAt;
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


//    @ManyToOne
//    @PrimaryKeyJoinColumn(name="USERID", referencedColumnName="id")
//    private ParentUser parentuser;




//    public FAQ(String question) {
//        this.question = question;
//    }
//}
}