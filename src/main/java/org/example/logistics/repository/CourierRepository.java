package org.example.logistics.repository;
import org.example.logistics.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> findByLocationAndCapacityGreaterThan(String location, int capacity);
}
