package org.example.logistics.service;


import org.example.logistics.entity.Courier;
import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    public Optional<Courier> findCourierById(Long courierId) {
        return courierRepository.findById(courierId);
    }

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Courier updateCourierLocation(Long id, String location) {
        Courier courier = courierRepository.findById(id).orElse(null);
        if (courier != null) {
            courier.setLocation(location);
            return courierRepository.save(courier);
        }
        return null;
    }

    public Optional<Courier> findAvailableCourier(String location) {
        List<Courier> couriers = courierRepository.findByLocationAndCapacityGreaterThan(location, 0);
        return couriers.stream().findFirst();
    }

    public Courier updateCourierCapacity(Long id, int capacity) {
        Courier courier = courierRepository.findById(id).orElse(null);
        if (courier != null) {
            courier.setCapacity(capacity);
            return courierRepository.save(courier);
        }
        return null;
    }
}
