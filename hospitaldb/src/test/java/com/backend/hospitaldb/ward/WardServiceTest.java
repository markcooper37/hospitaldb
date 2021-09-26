package com.backend.hospitaldb.ward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WardServiceTest {

    @Mock
    private WardDataAccessServicePG wardDataAccessServicePG;
    private WardService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new WardService(wardDataAccessServicePG);
    }

    @Test
    void canAddNewWard() {

        int wardId = 5;
        String wardName = "Oncology";
        Long hospital = 1L;

        Ward ward = new Ward(wardId, wardName, hospital);

        Mockito.when(wardDataAccessServicePG.insertWard(Mockito.any(Ward.class)))
                .thenReturn(1);

        underTest.addNewWard(ward);

        ArgumentCaptor<Ward> wardArgumentCaptor = ArgumentCaptor.forClass(Ward.class);
        Mockito.verify(wardDataAccessServicePG).insertWard(wardArgumentCaptor.capture());

        Ward newWard = wardArgumentCaptor.getValue();
        assertThat(newWard.getWardId()).isEqualTo(5);
        assertThat(newWard.getWardName()).isEqualTo("Oncology");
        assertThat(newWard.getHospital()).isEqualTo(1L);
    }

    @Test
    void canGetWards() {
        // Given
        List<Ward> wards = List.of(
                new Ward(1, "Hospital 1", 1L),
                new Ward(2, "Hospital 2", 1L));

        Mockito.when(wardDataAccessServicePG.selectAllWards()).thenReturn(wards);

        // When
        List<Ward> allWards = underTest.getWards();

        // Then
        assertThat(allWards).isEqualTo(wards);

    }

    @Test
    void canGetWard() {

        Ward ward1 = new Ward(1, "Hospital 1", 1L);
        Ward ward2 = new Ward(2, "Hospital 2", 1L);

        List<Ward> wards = List.of(
                ward1, ward2);

        Mockito.when(wardDataAccessServicePG.selectAllWards()).thenReturn(wards);

        Ward chosenWard = underTest.getWard(1);

        assertThat(chosenWard).isEqualTo(ward1);
    }

    @Test
    void canDeleteWard() {

        Ward ward1 = new Ward(1, "Hospital 1", 1L);
        Ward ward2 = new Ward(2, "Hospital 2", 1L);

        List<Ward> wards = new ArrayList<>();
        wards.add(ward1);
        wards.add(ward2);

        Mockito.when(wardDataAccessServicePG.selectAllWards()).thenReturn(wards);
        Mockito.when(wardDataAccessServicePG.deleteWard(ward1)).thenReturn(1);

        int result = underTest.deleteWard(1);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void canUpdateWard() {
        Ward ward1 = new Ward(1, "Hospital 1", 1L);
        Ward ward2 = new Ward(1, "Hospital 2", 2L);

        List<Ward> wards = List.of(
                ward1);

        Mockito.when(wardDataAccessServicePG.selectAllWards()).thenReturn(wards);
        Mockito.when(wardDataAccessServicePG.deleteWard(ward1)).thenReturn(1);
        Mockito.when(wardDataAccessServicePG.insertWard(ward2)).thenReturn(1);

        int result = underTest.updateWard(ward2);

        assertThat(result).isEqualTo(1);
    }
}
