package com.manglik.Rev1.Entity;

import com.manglik.Rev1.Validation.PersonNameValid;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Email(message = "Invalid email address")
    public String email;
    @PersonNameValid(message = "Name is required")
    public String name;

}
