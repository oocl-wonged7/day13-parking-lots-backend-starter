package org.afs.pakinglot.domain.exception;

public class CarAlreadyParkedException extends RuntimeException {
    public CarAlreadyParkedException() {
        super("The car is already parked.");
    }
}
