package org.example.logistics.service;

import org.example.logistics.entity.Courier;
import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.repository.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private CourierService courierService;

    @InjectMocks
    private ShipmentService shipmentService;

    @Test
    void testCreateShipment() {
        Shipment shipment = new Shipment();
        shipment.setSender("John Doe");
        shipment.setRecipient("Jane Doe");

        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        Shipment createdShipment = shipmentService.createShipment(shipment);

        assertEquals("John Doe", createdShipment.getSender());
        assertEquals("Jane Doe", createdShipment.getRecipient());
        verify(shipmentRepository, times(1)).save(shipment);
    }

    @Test
    void testUpdateShipmentStatus() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStatus(ShipmentStatus.CREATED);

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        shipmentService.updateShipmentStatus(1L, ShipmentStatus.DELIVERED);

        assertEquals(ShipmentStatus.DELIVERED, shipment.getStatus());
        verify(shipmentRepository, times(1)).save(shipment);
    }

    @Test
    void assignShipmentToCourier_Success() {
        // Given
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setLocation("Cityville");
        courier.setCapacity(2);

        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setDestination("Cityville");
        shipment.setStatus(ShipmentStatus.PENDING);

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(courierService.findAvailableCourier("Cityville")).thenReturn(Optional.of(courier));

        // When
        Optional<Shipment> result = shipmentService.assignShipmentToCourier(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(ShipmentStatus.ASSIGNED, result.get().getStatus());
        assertEquals(courier, result.get().getCourier());
        verify(courierService, times(1)).updateCourierCapacity(anyLong(), anyInt());
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
    }

    @Test
    void assignShipmentToCourier_ShipmentNotFound() {
        // Given
        when(shipmentRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Shipment> result = shipmentService.assignShipmentToCourier(1L);

        // Then
        assertFalse(result.isPresent());
        verify(courierService, never()).findAvailableCourier(anyString());
        verify(shipmentRepository, never()).save(any(Shipment.class));
    }

    @Test
    void assignShipmentToCourier_CourierNotFound() {
        // Given
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setDestination("Cityville");
        shipment.setStatus(ShipmentStatus.PENDING);

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(courierService.findAvailableCourier("Cityville")).thenReturn(Optional.empty());

        // When
        Optional<Shipment> result = shipmentService.assignShipmentToCourier(1L);

        // Then
        assertFalse(result.isPresent());
        verify(shipmentRepository, never()).save(any(Shipment.class));
    }

    @Test
    void trackShipment_Success() {
        // Given
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setLocation("456 Oak St, Cityville");

        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setCourier(courier);
        shipment.setStatus(ShipmentStatus.IN_TRANSIT);

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(courierService.findCourierById(1L)).thenReturn(Optional.of(courier));

        // When
        Optional<Shipment> result = shipmentService.trackShipment(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(ShipmentStatus.IN_TRANSIT, result.get().getStatus());
        assertEquals(courier, result.get().getCourier());
    }

    @Test
    void trackShipment_ShipmentNotFound() {
        // Given
        when(shipmentRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Shipment> result = shipmentService.trackShipment(1L);

        // Then
        assertFalse(result.isPresent());
        verify(courierService, never()).findCourierById(anyLong());
    }

    @Test
    void trackShipment_CourierNotFound() {
        // Given
        Courier courier = new Courier();
        courier.setId(1L);

        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setCourier(courier);
        shipment.setStatus(ShipmentStatus.IN_TRANSIT);

        when(shipmentRepository.findById(2L)).thenReturn(Optional.empty());

        // When
        Optional<Shipment> result = shipmentService.trackShipment(2L);

        // Then
        assertFalse(result.isPresent());
    }
}
