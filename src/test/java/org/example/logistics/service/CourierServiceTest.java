package org.example.logistics.service;

import org.example.logistics.entity.Courier;
import org.example.logistics.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierServiceTest {

    @InjectMocks
    private CourierService courierService;

    @Mock
    private CourierRepository courierRepository;

    @Test
    void testUpdateCourierLocation() {
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("XYZ");
        courier.setLocation("Cape Town");
        courier.setCapacity(3);

        when(courierRepository.save(any(Courier.class))).thenReturn(courier);


        when(courierRepository.findById(1L)).thenReturn(Optional.of(courier));
        Courier updatedCourier = courierService.updateCourierLocation(1L, "Jozi");

        assertEquals("XYZ", updatedCourier.getName());
        assertEquals("Jozi", updatedCourier.getLocation());
        verify(courierRepository, times(1)).save(courier);
    }
}
