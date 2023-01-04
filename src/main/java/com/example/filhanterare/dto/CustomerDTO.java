package com.example.filhanterare.dto;

import com.example.filhanterare.entities.ECustomer;

import java.util.Set;

public record CustomerDTO(
    long id,
    String firstname, String lastname,
    String address, String zipcode, String city,
    Set<ECustomer> customerType) {
}
