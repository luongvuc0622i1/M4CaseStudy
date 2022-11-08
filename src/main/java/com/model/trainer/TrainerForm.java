package com.model.trainer;

import com.model.AppUser;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TrainerForm {
    private Long id;

    private String name;

    private Date dateOfBirth;

    private String address;

    @ManyToOne
    @JoinColumn(name = "income_id")
    private Income income;

    private MultipartFile cvFile;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    public TrainerForm() {
    }
}
