package com.manglik.Rev1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {


    private int id;
    private String name;
    private int pid;

}
