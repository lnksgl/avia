package avia.cloud.gateway.auth.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRecord(
        @JsonProperty("username") String username,
        @JsonProperty("password") String password) {
}
