package com.model.trainer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.model.jwt.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date dateOfBirth;

    private String address;

    @ManyToOne
    @JoinColumn(name = "income_id")
    private TrainerIncome income;

    private String cvFile;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    public Trainer(String name, Date dateOfBirth, String address, TrainerIncome income, AppUser appUser) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.income = income;
        this.appUser = appUser;
    }
}
