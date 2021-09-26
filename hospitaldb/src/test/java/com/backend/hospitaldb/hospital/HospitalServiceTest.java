package com.backend.hospitaldb.hospital;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HospitalServiceTest {

    @Mock
    private HospitalDataAccessServicePG hospitalDataAccessServicePG;
    private HospitalService underTest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new HospitalService(hospitalDataAccessServicePG);
    }

    @Test
    void canGetHospitalByID() {

        // Given
        Hospital hospital = new Hospital(1L, "Daisy Hill", "Bank Street", "Newry", "Down", "UK", "BT35 8DR");
        List<Hospital> hospitals = List.of(hospital);

        Mockito.when(hospitalDataAccessServicePG.selectAllHospitals()).thenReturn(hospitals);

        // When
        Hospital myHospital = underTest.getHospital(1L);

        // Then
        assertThat(myHospital).isEqualTo(hospital);

    }

    @Test
    void willNotAddHospitalWhenHospitalNameIsEmpty() {
        // Given
        Hospital hospital = new Hospital();
        hospital.setHospitalName(" ");

        Mockito.when(hospitalDataAccessServicePG.insertHospital(hospital)).thenReturn(0);

        // When
        assertThatThrownBy(() -> {
            underTest.addNewHospital(hospital);
        }).hasMessage("Error - please try again. Make sure all information entered is correct.");
    }

    @Test
    void canAddNewHospital() {
        // Given
        Hospital royal = new Hospital(1L, "Daisy Hill", "Bank Street", "Newry", "Down", "UK", "BT35 8DR");

        // When
        Mockito.when(hospitalDataAccessServicePG.insertHospital(Mockito.any(Hospital.class)))
                .thenReturn(1);

        underTest.addNewHospital(royal);
        //Then

        ArgumentCaptor<Hospital> hospitalArgumentCaptor = ArgumentCaptor.forClass(Hospital.class);
        Mockito.verify(hospitalDataAccessServicePG).insertHospital(hospitalArgumentCaptor.capture());

        Hospital hospital= hospitalArgumentCaptor.getValue();
        assertThat(hospital.getHospitalID()).isEqualTo(1L);
        assertThat(hospital.getHospitalName()).isEqualTo("Daisy Hill");
        assertThat(hospital.getStreet()).isEqualTo("Bank Street");
        assertThat(hospital.getCity()).isEqualTo("Newry");
        assertThat(hospital.getState()).isEqualTo("Down");
        assertThat(hospital.getCountry()).isEqualTo("UK");
        assertThat(hospital.getPostalCode()).isEqualTo("BT35 8DR");
    }

    @Test
    void canGetHospitals() {
        // Given
        List<Hospital> hospitals = List.of(
                new Hospital(1L, "Daisy Hill", "Bank Street", "Newry", "Down", "UK", "BT35 8DR"));

        Mockito.when(hospitalDataAccessServicePG.selectAllHospitals()).thenReturn(hospitals);

        // When
        List<Hospital> allHospitals = underTest.getHospitals();

        // Then
        assertThat(allHospitals).isEqualTo(hospitals);

    }

    @Test
    void canDeleteHospital() {
        Hospital hospital1 = new Hospital(1L, "Daisy Hill", "Bank Street", "Newry", "Down", "UK", "BT35 8DR");
        Hospital hospital2 = new Hospital(2L, "Holby City", "Eldon Avenue", "Borehamwood", "Hertfordshire", "UK", "WD6 1NL");

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital1);
        hospitals.add(hospital2);

        Mockito.when(hospitalDataAccessServicePG.selectAllHospitals()).thenReturn(hospitals);
        Mockito.when(hospitalDataAccessServicePG.deleteHospital(hospital1)).thenReturn(1);

        int result = underTest.deleteHospital(1L);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void canUpdateHospital() {
        Hospital hospital1 = new Hospital(1L, "Daisy Hill", "Bank Street", "Newry", "Down", "UK", "BT35 8DR");
        Hospital hospital2 = new Hospital(1L, "Holby City", "Eldon Avenue", "Borehamwood", "Hertfordshire", "UK", "WD6 1NL");


        List<Hospital> hospitals = List.of(
                hospital1);

        Mockito.when(hospitalDataAccessServicePG.selectAllHospitals()).thenReturn(hospitals);
        Mockito.when(hospitalDataAccessServicePG.deleteHospital(hospital1)).thenReturn(1);
        Mockito.when(hospitalDataAccessServicePG.insertHospital(hospital2)).thenReturn(1);

        int result = underTest.updateHospital(hospital2);

        assertThat(result).isEqualTo(1);
    }
}
