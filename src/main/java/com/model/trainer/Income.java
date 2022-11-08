package com.model.trainer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double salary;
    private double bonus;

    public double getIncome() {
        return salary+bonus;
    }
}
