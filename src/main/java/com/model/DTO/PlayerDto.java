package com.model.DTO;

import com.model.jwt.AppUser;
import com.model.player.Performance;
import com.model.player.PlayerIncome;
import com.model.player.Position;
import com.model.player.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;

@Data
@NoArgsConstructor

public class PlayerDto {
    private Long id;

    private String name;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    private String address;

    private double height;

    private double weight;

    private AppUser appUser;

    private Position position;

    private Performance performance;

    private PlayerIncome playerIncome;

    private MultipartFile profile;

    private MultipartFile image;

    private Status status;

}
