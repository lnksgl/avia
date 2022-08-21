package avia.cloud.gateway.auth.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignupRecord(
        @JsonProperty("username") String username,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("city") Long city) {
}
