package com.machlou.patientmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PATIENTS")
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private boolean sick;
    private int score;
    @Column(name = "birth_date")
    private Date birthDate;
}
