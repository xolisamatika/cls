package org.example.logistics.service;


import org.example.logistics.entity.Courier;
import org.example.logistics.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

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

    public Courier assignShipmentToCourier(Long courierId) {
        Courier courier = courierRepository.findById(courierId).orElse(null);
        if (courier != null && courier.getCapacity() > 0) {
            // Logic to assign shipment to courier
            courier.setCapacity(courier.getCapacity() - 1);
            return courierRepository.save(courier);
        }
        return null;
    }
}
