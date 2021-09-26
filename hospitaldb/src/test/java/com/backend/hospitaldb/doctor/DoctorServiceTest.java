package com.backend.hospitaldb.doctor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorServiceTest {

    @Mock
    private DoctorDataAccessServicePG doctorDataAccessService;
    private DoctorService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DoctorService (doctorDataAccessService);
    }

    @Test
    void canAddDoctor() {
        // Given
        Doctor joe = new Doctor(12L, "Joe", "Bloggs", "Cardiology", 1L);

        // When
        Mockito.when(doctorDataAccessService.insertDoctor(Mockito.any(Doctor.class)))
                .thenReturn(1);

        underTest.addNewDoctor(joe);
        //Then

        ArgumentCaptor<Doctor> doctorArgumentCaptor = ArgumentCaptor.forClass(Doctor.class);
        Mockito.verify(doctorDataAccessService).insertDoctor(doctorArgumentCaptor.capture());

        Doctor doctor = doctorArgumentCaptor.getValue();
        assertThat(doctor.getDoctorID()).isEqualTo(12L);
        assertThat(doctor.getFirstName()).isEqualTo("Joe");
        assertThat(doctor.getLastName()).isEqualTo("Bloggs");
        assertThat(doctor.getSpeciality()).isEqualTo("Cardiology");
        assertThat(doctor.getHospital()).isEqualTo(1L);
    }

    @Test
    void canGetDoctors() {
        // Given
        List<Doctor> doctors = List.of(
                new Doctor(1L, "Bob", "Smith", "Cardiology", 1L),
                new Doctor(2L, "Susan", "Jones", "Oncology", 1L));

        Mockito.when(doctorDataAccessService.selectAllDoctors()).thenReturn(doctors);

        // When
        List<Doctor> allDoctors = underTest.getDoctors();

        // Then
        assertThat(allDoctors).isEqualTo(doctors);

    }

    @Test
    void canGetDoctor() {

        Doctor doctor1 = new Doctor(1L, "Bob", "Smith", "Cardiology", 1L);
        Doctor doctor2 = new Doctor(2L, "Susan", "Jones", "Oncology", 1L);

        List<Doctor> doctors = List.of(
                doctor1, doctor2);

        Mockito.when(doctorDataAccessService.selectAllDoctors()).thenReturn(doctors);

        Doctor chosenDoctor = underTest.getDoctor(1L);

        assertThat(chosenDoctor).isEqualTo(doctor1);
    }

    @Test
    void canDeleteDoctor() {

        Doctor doctor1 = new Doctor(1L, "Bob", "Smith", "Cardiology", 1L);
        Doctor doctor2 = new Doctor(2L, "Susan", "Jones", "Oncology", 1L);

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);

        Mockito.when(doctorDataAccessService.selectAllDoctors()).thenReturn(doctors);
        Mockito.when(doctorDataAccessService.deleteDoctor(doctor1)).thenReturn(1);

        int result = underTest.deleteDoctor(1L);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void canUpdateDoctor() {
        Doctor doctor1 = new Doctor(1L, "Bob", "Smith", "Cardiology", 1L);
        Doctor doctor2 = new Doctor(1L, "Susan", "Jones", "Oncology", 1L);

        List<Doctor> doctors = List.of(
                doctor1);

        Mockito.when(doctorDataAccessService.selectAllDoctors()).thenReturn(doctors);
        Mockito.when(doctorDataAccessService.deleteDoctor(doctor1)).thenReturn(1);
        Mockito.when(doctorDataAccessService.insertDoctor(doctor2)).thenReturn(1);

        int result = underTest.updateDoctor(doctor2);

        assertThat(result).isEqualTo(1);
    }


}
