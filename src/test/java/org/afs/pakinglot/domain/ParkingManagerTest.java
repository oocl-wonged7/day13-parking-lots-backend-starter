package org.afs.pakinglot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
            "ABC123, 1",
            "DEF456, 2",
            "GHI789, 3"
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
        String plateNumber = "JKL012";
        parkingManager.park(plateNumber, 1);

        // When
        Car car = parkingManager.fetch(plateNumber);

        // Then
        assertNotNull(car);
    }

    @Test
    void givenInvalidPlateNumber_whenFetchCar_thenThrowsException() {
        // Given
        String plateNumber = "XYZ999";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> parkingManager.fetch(plateNumber));
    }
}