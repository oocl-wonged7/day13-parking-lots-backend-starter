package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;

public class ParkingManager {
    private List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, "The Plaza Park", 9),
            new ParkingLot(2, "City Mall Garage", 12),
            new ParkingLot(3, "Office Tower Parking", 9)
    );

    private ParkingBoy sequentialParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
    private ParkingBoy maxAvailableParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
    private ParkingBoy availableRateParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());

    public List<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public Ticket park(String plateNumber, int strategy) {
        ParkingBoy parkingBoy = getParkingBoyByStrategy(strategy);
        Car car = new Car(plateNumber);
        return parkingBoy.park(car);
    }

    public Car fetch(String plateNumber) {
        for (ParkingLot parkingLot : parkingLots) {
            for (Ticket ticket : parkingLot.getTickets()) {
                if (ticket.plateNumber().equals(plateNumber)) {
                    return parkingLot.fetch(ticket);
                }
            }
        }
        throw new IllegalArgumentException("Car with plate number " + plateNumber + " not found");
    }

    private ParkingBoy getParkingBoyByStrategy(int strategyId) {
        switch (strategyId) {
            case 1:
                return sequentialParkingBoy;
            case 2:
                return maxAvailableParkingBoy;
            case 3:
                return availableRateParkingBoy;
            default:
                throw new IllegalArgumentException("Invalid parking strategy ID");
        }
    }
}

