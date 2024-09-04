package org.example.logistics.controller;

import org.example.logistics.entity.Courier;
import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
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

    @GetMapping("/{id}/track")
    public ResponseEntity<Shipment> trackShipment(@PathVariable Long id) {
        return shipmentService.trackShipment(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateShipmentStatus(@PathVariable Long id, @RequestParam ShipmentStatus status) {
        shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<Shipment> assignShipmentToCourier(@PathVariable Long id) {
        return shipmentService.assignShipmentToCourier(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
