package com.model.trainer;

import com.model.jwt.AppUser;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public MultipartFile getCvFile() {
        return cvFile;
    }

    public void setCvFile(MultipartFile cvFile) {
        this.cvFile = cvFile;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
