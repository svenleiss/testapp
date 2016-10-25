package com.mhp.insideApp.persistence.insideApplications.entity;

import com.mhp.insideApp.insideApplications.Greetings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Greetings")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor

public class GreetingEntity {

    @Column
    @Id
    @GeneratedValue()
    @Wither
    private Long id;

    @Column
    @Wither
    private String userName;

    @Column
    @Wither
    private String message;

    public GreetingEntity(Greetings greetings) {
        this.id = greetings.getId();
        this.message = greetings.getMessage();
        this.userName = greetings.getUserName();
    }

}
