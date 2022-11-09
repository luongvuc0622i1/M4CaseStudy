package com.model.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "playerIncome")
public class PlayerIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double salary;

    private double playTime;
    private double bonus;

    public double getIncome() {
        return salary + (salary * 0.1 * playTime) + bonus;
    }
}
