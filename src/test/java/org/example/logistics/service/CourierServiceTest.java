package org.example.logistics.service;

import org.example.logistics.entity.Courier;
import org.example.logistics.repository.CourierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CourierServiceTest {

    @InjectMocks
    private CourierService courierService;

    @Mock
    private CourierRepository courierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateCourierLocation() {
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("XYZ");
        courier.setLocation("Cape Town");
        courier.setCapacity(3);

        when(courierRepository.save(any(Courier.class))).thenReturn(courier);

        Courier updatedCourier = courierService.updateCourierLocation(1L, "Jozi");

        assertEquals("XYZ", updatedCourier.getName());
        assertEquals("Jozi", updatedCourier.getLocation());
        verify(courierRepository, times(1)).save(courier);
    }
}
