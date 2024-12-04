package org.afs.pakinglot.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkingManager")
public class ParkingManagerController {
    private final ParkingManager parkingManager = new ParkingManager();

    // Get /allParkingLots
    // response: List<ParkingLot>
    @GetMapping("/allParkingLots")
    public List<ParkingLot> getAllParkingLots() {
        return parkingManager.getAllParkingLots();
    }
}
