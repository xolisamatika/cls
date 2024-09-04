package org.example.logistics.service;

import org.example.logistics.entity.Courier;
import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CourierService courierService;

    public ShipmentService(ShipmentRepository shipmentRepository, CourierService courierService) {
        this.shipmentRepository = shipmentRepository;
        this.courierService = courierService;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

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

    public void updateShipmentStatus(Long id, ShipmentStatus status) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        if (shipment.isPresent()) {
            Shipment s = shipment.get();
            s.setStatus(status);
            s.setUpdatedAt(LocalDateTime.now());
            shipmentRepository.save(s);
        }
    }

    public Optional<Shipment> assignShipmentToCourier(Long shipmentId) {
        Optional<Shipment> shipmentOpt = shipmentRepository.findById(shipmentId);
        if (shipmentOpt.isPresent()) {
            Shipment shipment = shipmentOpt.get();
            Optional<Courier> courierOpt = courierService.findAvailableCourier(shipment.getDestination());
            if (courierOpt.isPresent() ) {
                Courier courier = courierOpt.get();
                if (courier.getCapacity() > 0) {
                    shipment.setCourier(courier);
                    shipment.setStatus(ShipmentStatus.ASSIGNED);
                    shipmentRepository.save(shipment);
                    courierService.updateCourierCapacity(courier.getId(), courier.getCapacity() - 1);
                }
                return Optional.of(shipment);
            }
        }
        return Optional.empty();
    }

    public Optional<Shipment> trackShipment(Long shipmentId) {
        Optional<Shipment> shipmentOpt = shipmentRepository.findById(shipmentId);
        if (shipmentOpt.isPresent()) {
            Shipment shipment = shipmentOpt.get();
            if (shipment.getCourier() != null) {
                Optional<Courier> courierOpt = courierService.findCourierById(shipment.getCourier().getId());
                if (courierOpt.isPresent()) {
                    Courier courier = courierOpt.get();
                    shipment.setCourier(courier);
                }
            }
            return Optional.of(shipment);
        }
        return Optional.empty();
    }
}
