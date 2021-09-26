package com.backend.hospitaldb.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientServiceTest {

    @Mock
    private PatientDataAccessServicePG patientDataAccessServicePG;
    private PatientService underTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new PatientService (patientDataAccessServicePG);
    }

    @Test
    void canGetPatients() {

        // Given
        List<Patient> patients = List.of(
                new Patient(1L, "Isabella","Smith", SEX.F, LocalDate.of(1980, 4, 20),41, SMOKER.NO,"Lung Disease",2, LocalDate.of(2020, 3, 30),LocalDate.of(2020, 5, 30), COVIDRISK.High, ASSESSMENTRISK.High, 1L));

        Mockito.when(patientDataAccessServicePG.selectAllPatients()).thenReturn(patients);

        // When
        List<Patient> allPatients = underTest.getPatients();

        // Then
        assertThat(allPatients).isEqualTo(patients);

    }

    @Test
    void canAddPatient() {

        Patient patient = new Patient(1L, "Isabella","Smith", SEX.F, LocalDate.of(1980, 4, 20),41, SMOKER.NO,"Lung Disease",2, LocalDate.of(2020, 3, 30), LocalDate.of(2020, 5, 30), COVIDRISK.High, ASSESSMENTRISK.High, 1L);

        Mockito.when(patientDataAccessServicePG.insertPatient(Mockito.any(Patient.class)))
                .thenReturn(1);

        underTest.addNewPatient(patient);

        ArgumentCaptor<Patient> patientArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        Mockito.verify(patientDataAccessServicePG).insertPatient(patientArgumentCaptor.capture());

        Patient newPatient = patientArgumentCaptor.getValue();
        assertThat(newPatient.getId()).isEqualTo(1L);
        assertThat(newPatient.getFirstName()).isEqualTo("Isabella");
        assertThat(newPatient.getLastName()).isEqualTo("Smith");
        assertThat(newPatient.getSex()).isEqualTo(SEX.F);
        assertThat(newPatient.getDob()).isEqualTo(LocalDate.of(1980, 4, 20));
        assertThat(newPatient.getAge()).isEqualTo(41);
        assertThat(newPatient.getSmoker()).isEqualTo(SMOKER.NO);
        assertThat(newPatient.getIllness()).isEqualTo("Lung Disease");
        assertThat(newPatient.getWard()).isEqualTo(2);
        assertThat(newPatient.getDateAdmission()).isEqualTo(LocalDate.of(2020, 3, 30));
        assertThat(newPatient.getDateRelease()).isEqualTo(LocalDate.of(2020, 5, 30));
        assertThat(newPatient.getCovidrisk()).isEqualTo(COVIDRISK.High);
        assertThat(newPatient.getAssessmentrisk()).isEqualTo(ASSESSMENTRISK.High);
        assertThat(newPatient.getDoctor()).isEqualTo(1L);
    }

    @Test
    void canGetPatient() {

        Patient patient1 = new Patient(1L, "Isabella","Smith", SEX.F, LocalDate.of(1980, 4, 20),41, SMOKER.NO,"Lung Disease",2, LocalDate.of(2020, 3, 30),LocalDate.of(2020, 5, 30), COVIDRISK.High, ASSESSMENTRISK.High, 1L);
        Patient patient2 = new Patient(1L, "Susan", "Jones", SEX.F, LocalDate.of(1952, 7, 13), 69, SMOKER.YES, "Cancer", 7, LocalDate.of(2021, 8, 17), LocalDate.of(2021, 9, 12), COVIDRISK.High, ASSESSMENTRISK.High, 1L);

        List<Patient> patients = List.of(
                patient1, patient2);

        Mockito.when(patientDataAccessServicePG.selectAllPatients()).thenReturn(patients);

        Patient chosenPatient = underTest.getPatient(1L);

        assertThat(chosenPatient).isEqualTo(patient1);
    }

    @Test
    void canDeletePatient() {

        Patient patient1 = new Patient(1L, "Isabella","Smith", SEX.F, LocalDate.of(1980, 4, 20),41, SMOKER.NO,"Lung Disease",2, LocalDate.of(2020, 3, 30),LocalDate.of(2020, 5, 30), COVIDRISK.High, ASSESSMENTRISK.High, 1L);
        Patient patient2 = new Patient(1L, "Susan", "Jones", SEX.F, LocalDate.of(1952, 7, 13), 69, SMOKER.YES, "Cancer", 7, LocalDate.of(2021, 8, 17), LocalDate.of(2021, 9, 12), COVIDRISK.High, ASSESSMENTRISK.High, 1L);

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        Mockito.when(patientDataAccessServicePG.selectAllPatients()).thenReturn(patients);
        Mockito.when(patientDataAccessServicePG.deletePatient(patient1)).thenReturn(1);

        int result = underTest.deletePatient(1L);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void canUpdatePatient() {
        Patient patient1 = new Patient(1L, "Isabella","Smith", SEX.F, LocalDate.of(1980, 4, 20),41, SMOKER.NO,"Lung Disease",2, LocalDate.of(2020, 3, 30),LocalDate.of(2020, 5, 30), COVIDRISK.High, ASSESSMENTRISK.High, 1L);
        Patient patient2 = new Patient(1L, "Susan", "Jones", SEX.F, LocalDate.of(1952, 7, 13), 69, SMOKER.YES, "Cancer", 7, LocalDate.of(2021, 8, 17), LocalDate.of(2021, 9, 12), COVIDRISK.High, ASSESSMENTRISK.High, 1L);


        List<Patient> patients = List.of(
                patient1);

        Mockito.when(patientDataAccessServicePG.selectAllPatients()).thenReturn(patients);
        Mockito.when(patientDataAccessServicePG.deletePatient(patient1)).thenReturn(1);
        Mockito.when(patientDataAccessServicePG.insertPatient(patient2)).thenReturn(1);

        int result = underTest.updatePatient(patient2);

        assertThat(result).isEqualTo(1);
    }
}
