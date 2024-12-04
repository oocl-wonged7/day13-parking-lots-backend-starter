package org.afs.pakinglot.domain;

import org.springframework.web.bind.annotation.*;

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

    // Post /park
    // request: String plateNumber, int strategy
    // response: Ticket
    @PostMapping("/park")
    public Ticket park(@RequestParam String plateNumber, @RequestParam int strategy) {
        return parkingManager.park(plateNumber, strategy);
    }

}
