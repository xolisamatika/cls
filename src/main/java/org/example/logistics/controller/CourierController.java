package org.example.logistics.controller;

import org.example.logistics.entity.Courier;
import org.example.logistics.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;

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

    @PutMapping("/{id}/assign-shipment")
    public ResponseEntity<Courier> assignShipmentToCourier(@PathVariable Long id) {
        Courier courier = courierService.assignShipmentToCourier(id);
        if (courier != null) {
            return ResponseEntity.ok(courier);
        }
        return ResponseEntity.notFound().build();
    }
}
