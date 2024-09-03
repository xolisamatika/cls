package org.example.logistics.service;

import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public Shipment createShipment(Shipment shipment) {
        shipment.setStatus(ShipmentStatus.CREATED);
        shipment.setCreatedAt(LocalDateTime.now());
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Long id, Shipment shipment) {
        Optional<Shipment> existingShipment = shipmentRepository.findById(id);
        if (existingShipment.isPresent()) {
            Shipment s = existingShipment.get();
            s.setRecipient(shipment.getRecipient());
            s.setDestination(shipment.getDestination());
            s.setUpdatedAt(LocalDateTime.now());
            return shipmentRepository.save(s);
        }
        return null;
    }

    public Shipment trackShipment(Long id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    public void updateShipmentStatus(Long id, ShipmentStatus status) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        if (shipment.isPresent()) {
            Shipment s = shipment.get();
            s.setStatus(status);
            s.setUpdatedAt(LocalDateTime.now());
            shipmentRepository.save(s);
        }
    }
}
