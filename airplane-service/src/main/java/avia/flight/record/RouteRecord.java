package avia.flight.record;

import java.time.Instant;

public record RouteRecord(Long id, AirplaneRecord airplane,
                          RouteCitiesRecord routeCities, Instant date) {
}
