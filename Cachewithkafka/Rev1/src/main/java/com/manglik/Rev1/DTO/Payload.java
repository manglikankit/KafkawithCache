package com.manglik.Rev1.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
//@AllArgsConstructor
@NoArgsConstructor
public class Payload {
    private String data;
    private String notificationType;
    private String pid;

    @JsonCreator
    public Payload(
            @JsonProperty("data") String data,
            @JsonProperty("notificationType") String notificationType,
            @JsonProperty("pid") String pid) {
        this.data = data;
        this.notificationType = notificationType;
        this.pid = pid;
    }

}
