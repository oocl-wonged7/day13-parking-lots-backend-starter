package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.dto.FetchRequest;
import org.afs.pakinglot.domain.dto.ParkRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingManager")
@CrossOrigin(origins = "http://localhost:3000")
public class ParkingManagerController {
    private final ParkingManager parkingManager = new ParkingManager();

    @GetMapping("/allParkingLots")
    public List<ParkingLot> getAllParkingLots() {
        System.out.println("GET /allParkingLots");
        return parkingManager.getAllParkingLots();
    }

    @PostMapping("/park")
    public Ticket park(@RequestBody ParkRequest request) {
        System.out.println("POST /park " + request.getPlateNumber() + " " + request.getStrategy());
        return parkingManager.park(request.getPlateNumber(), request.getStrategy());
    }

    @PostMapping("/fetch")
    public Car fetch(@RequestBody FetchRequest request) {
        System.out.println("POST /fetch " + request.getPlateNumber());
        return parkingManager.fetch(request.getPlateNumber());
    }
}