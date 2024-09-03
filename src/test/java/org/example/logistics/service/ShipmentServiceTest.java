package org.example.logistics.service;

import org.example.logistics.entity.Shipment;
import org.example.logistics.entity.ShipmentStatus;
import org.example.logistics.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ShipmentServiceTest {

    @InjectMocks
    private ShipmentService shipmentService;

    @Mock
    private ShipmentRepository shipmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateShipment() {
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
    public void testUpdateShipmentStatus() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setStatus(ShipmentStatus.CREATED);

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        shipmentService.updateShipmentStatus(1L, ShipmentStatus.DELIVERED);

        assertEquals(ShipmentStatus.DELIVERED, shipment.getStatus());
        verify(shipmentRepository, times(1)).save(shipment);
    }
}
