package com.manglik.Rev1.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@Builder
@EqualsAndHashCode
//@AllArgsConstructor
@NoArgsConstructor
    public class PersonDTO {
    public String name;
    public String email;

    public List<Order> orderList;
    @JsonCreator
    public PersonDTO(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("orderList") List<Order> orderList) {
        this.name = name;
        this.email = email;
        this.orderList = orderList;
    }

}
