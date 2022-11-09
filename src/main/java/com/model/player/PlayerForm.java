package com.model.player;

import com.model.jwt.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
public class PlayerForm {
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

    private MultipartFile profile;

    private MultipartFile image;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
