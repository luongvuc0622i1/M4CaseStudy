package com.model.trainer;

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
public class TrainerForm {
    private Long id;

    private String name;

    private Date dateOfBirth;

    private String address;

    @ManyToOne
    @JoinColumn(name = "income_id")
    private TrainerIncome income;

    private MultipartFile cvFile;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

}
