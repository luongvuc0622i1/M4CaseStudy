package com.model.trainer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trainerIncome")
public class TrainerIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double salary;
    private double bonus;

    public double getIncome() {
        return salary+bonus;
    }
}
