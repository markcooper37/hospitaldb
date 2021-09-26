package com.backend.hospitaldb.patient;

import java.time.LocalDate;

public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private SEX sex;
    private LocalDate dob;
    private Integer age;
    private SMOKER smoker;
    private String illness;
    private Integer ward;
    private LocalDate dateAdmission;
    private LocalDate dateRelease;
    private COVIDRISK covidrisk;
    private ASSESSMENTRISK assessmentrisk;
    private Long doctor;

    public Patient() {

    }

    public Patient(Long id, String firstName, String lastName, SEX sex, LocalDate dob, Integer age, SMOKER smoker, String illness, Integer ward, LocalDate dateAdmission, LocalDate dateRelease, COVIDRISK covidrisk, ASSESSMENTRISK assessmentrisk, Long doctor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dob = dob;
        this.age = age;
        this.smoker = smoker;
        this.illness = illness;
        this.ward = ward;
        this.dateAdmission = dateAdmission;
        this.dateRelease = dateRelease;
        this.covidrisk = covidrisk;
        this.assessmentrisk = assessmentrisk;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SMOKER getSmoker() {
        return smoker;
    }

    public void setSmoker(SMOKER smoker) {
        this.smoker = smoker;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward;
    }

    public LocalDate getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(LocalDate dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    public COVIDRISK getCovidrisk() {
        return covidrisk;
    }

    public void setCovidrisk(COVIDRISK covidrisk) {
        this.covidrisk = covidrisk;
    }

    public ASSESSMENTRISK getAssessmentrisk() {
        return assessmentrisk;
    }

    public void setAssessmentrisk(ASSESSMENTRISK assessmentrisk) {
        this.assessmentrisk = assessmentrisk;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", dob=" + dob +
                ", age=" + age +
                ", smoker=" + smoker +
                ", illness='" + illness + '\'' +
                ", ward=" + ward +
                ", dateAdmission=" + dateAdmission +
                ", dateRelease=" + dateRelease +
                ", covidrisk=" + covidrisk +
                ", assessmentrisk=" + assessmentrisk +
                ", doctor=" + doctor +
                '}';
    }
}

