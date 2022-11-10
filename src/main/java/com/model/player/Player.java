package com.model.player;

import com.model.jwt.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date dateOfBirth;

    private String address;

    private double height;

    private double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne
    @JoinColumn(name = "playerIncome_id")
    private PlayerIncome playerIncome;

    private String profile;

    private String image;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public double getBMI() {
        return weight / (height * height);
    }

    public Player(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
