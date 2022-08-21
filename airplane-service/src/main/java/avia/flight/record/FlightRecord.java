package avia.flight.record;

public record FlightRecord(Long id, String name, String place, Long userId, RouteRecord route) {
}
