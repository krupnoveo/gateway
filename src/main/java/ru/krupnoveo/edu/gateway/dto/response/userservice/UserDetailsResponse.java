package ru.krupnoveo.edu.gateway.dto.response.userservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
public class UserDetailsResponse {

    private UUID id;
    private String username;
    private List<Authority> authorities;

    // Inner class for Authority
    @Getter
    @Setter
    @Data
    public static class Authority {
        @JsonProperty("authority")
        private String authority;
    }
}
