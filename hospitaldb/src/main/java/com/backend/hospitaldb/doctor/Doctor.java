package com.backend.hospitaldb.doctor;

public class Doctor {

    private Long doctorID;
    private String firstName;
    private String lastName;
    private String speciality;
    private Long hospital;

    public Doctor() {

    }

    public Doctor(Long doctorID, String firstName, String lastName, String speciality, Long hospital) {
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.hospital = hospital;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Long getHospital() {
        return hospital;
    }

    public void setHospital(Long hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", hospital=" + hospital +
                '}';
    }
}
