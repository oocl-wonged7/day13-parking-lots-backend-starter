package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.exception.CarAlreadyParkedException;
import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;
import java.util.regex.Pattern;

public class ParkingManager {
    private static final Pattern LICENSE_PLATE_PATTERN = Pattern.compile("^[A-Z]{2}-\\d{4}$");

    private final List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, "The Plaza Park", 9),
            new ParkingLot(2, "City Mall Garage", 12),
            new ParkingLot(3, "Office Tower Parking", 9)
    );

    private final ParkingBoy standardParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
    private final ParkingBoy smartParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
    private final ParkingBoy superSmartParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());

    public List<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public Ticket park(String plateNumber, String strategy) {
        validatePlateNumber(plateNumber);
        if (isCarAlreadyParked(plateNumber)) {
            throw new CarAlreadyParkedException();
        }
        ParkingBoy parkingBoy = getParkingBoyByStrategy(strategy);
        Car car = new Car(plateNumber);
        return parkingBoy.park(car);
    }

    public Car fetch(String plateNumber) {
        validatePlateNumber(plateNumber);
        return parkingLots.stream()
                .flatMap(parkingLot -> parkingLot.getTickets().stream())
                .filter(ticket -> ticket.plateNumber().equals(plateNumber))
                .findFirst()
                .map(ticket -> parkingLots.stream()
                        .filter(parkingLot -> parkingLot.getTickets().contains(ticket))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Car with plate number " + plateNumber + " not found"))
                        .fetch(ticket))
                .orElseThrow(() -> new IllegalArgumentException("Car with plate number " + plateNumber + " not found"));
    }

    public void validatePlateNumber(String plateNumber) {
        if (plateNumber == null || !LICENSE_PLATE_PATTERN.matcher(plateNumber).matches()) {
            throw new IllegalArgumentException("Invalid license plate number: " + plateNumber);
        }
    }

    private boolean isCarAlreadyParked(String plateNumber) {
        return parkingLots.stream()
                .flatMap(parkingLot -> parkingLot.getTickets().stream())
                .anyMatch(ticket -> ticket.plateNumber().equals(plateNumber));
    }

    private ParkingBoy getParkingBoyByStrategy(String strategy) {
        switch (strategy.toUpperCase()) {
            case "STANDARD":
                return standardParkingBoy;
            case "SMART":
                return smartParkingBoy;
            case "SUPERSMART":
                return superSmartParkingBoy;
            default:
                throw new IllegalArgumentException("Invalid parking strategy: " + strategy);
        }
    }
}

