package org.example.logistics.controller;

import org.example.logistics.entity.Courier;
import org.example.logistics.service.CourierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couriers")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        Courier createdCourier = courierService.createCourier(courier);
        return ResponseEntity.ok(createdCourier);
    }

    @GetMapping
    public ResponseEntity<List<Courier>> getAllCouriers() {
        List<Courier> couriers = courierService.getAllCouriers();
        return ResponseEntity.ok(couriers);
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<Courier> updateCourierLocation(@PathVariable Long id, @RequestParam String location) {
        Courier courier = courierService.updateCourierLocation(id, location);
        if (courier != null) {
            return ResponseEntity.ok(courier);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/capacity")
    public ResponseEntity<Courier> updateCourierCapacity(@PathVariable Long id, @RequestParam int capacity) {
        Courier courier = courierService.updateCourierCapacity(id, capacity);
        if (courier != null) {
            return ResponseEntity.ok(courier);
        }
        return ResponseEntity.notFound().build();
    }
}
