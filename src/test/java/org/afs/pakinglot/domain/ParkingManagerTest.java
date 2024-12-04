package org.afs.pakinglot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {
    private ParkingManager parkingManager;

    @BeforeEach
    void setUp() {
        parkingManager = new ParkingManager();
    }

    @Test
    void givenParkingManager_whenGetAllParkingLots_thenReturnsAllParkingLots() {
        // Given
        // ParkingManager is set up

        // When
        List<ParkingLot> parkingLots = parkingManager.getAllParkingLots();

        // Then
        assertEquals(3, parkingLots.size());
    }

    @ParameterizedTest
    @CsvSource({
            "AB-1234, 1",
            "XY-5678, 2",
            "XD-6789, 3"
    })
    void givenStrategy_whenParkCar_thenParksCar(String plateNumber, int strategy) {
        // When
        Ticket ticket = parkingManager.park(plateNumber, strategy);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void givenValidPlateNumber_whenFetchCar_thenReturnsCar() {
        // Given
        String plateNumber = "AB-1234";
        parkingManager.park(plateNumber, 1);

        // When
        Car car = parkingManager.fetch(plateNumber);

        // Then
        assertNotNull(car);
    }

    @Test
    void givenInvalidPlateNumber_whenFetchCar_thenThrowsException() {
        // Given
        String plateNumber = "AB-1234";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> parkingManager.fetch(plateNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"AB-1234", "XY-5678"})
    void givenValidPlateNumber_whenValidatePlateNumber_thenNoException(String plateNumber) {
        // When & Then
        parkingManager.validatePlateNumber(plateNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A-1234", "AB1234", "1234-AB", "AB-123", "ABC-1234", "", " "})
    void givenInvalidPlateNumber_whenValidatePlateNumber_thenThrowsException(String plateNumber) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> parkingManager.validatePlateNumber(plateNumber));
    }
}