package com.model.player;

import com.model.jwt.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

//    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
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

    public Player(String name, Date dateOfBirth, String address, double height, double weight, AppUser appUser, Position position, Performance performance, PlayerIncome playerIncome, String profile, String image, Status status) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.appUser = appUser;
        this.position = position;
        this.performance = performance;
        this.playerIncome = playerIncome;
        this.profile = profile;
        this.image = image;
        this.status = status;
    }
}
