package avia.flight.repository;

import avia.flight.entity.RouteCities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteCitiesRepository extends JpaRepository<RouteCities, Long> {
}
