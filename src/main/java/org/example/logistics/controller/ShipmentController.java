package org.example.logistics.controller;

import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.createShipment(shipment);
        return ResponseEntity.ok(createdShipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Long id, @RequestBody Shipment shipment) {
        Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
        if (updatedShipment != null) {
            return ResponseEntity.ok(updatedShipment);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> trackShipment(@PathVariable Long id) {
        Shipment shipment = shipmentService.trackShipment(id);
        if (shipment != null) {
            return ResponseEntity.ok(shipment);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateShipmentStatus(@PathVariable Long id, @RequestParam ShipmentStatus status) {
        shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
