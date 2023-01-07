package com.example.filhanterare.entities;

import com.example.filhanterare.dto.CustomerDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Id
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String zipcode;
    @Column(nullable = false)
    private String city;

    @ElementCollection(targetClass = ECustomer.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column
    private Set<ECustomer> customerType;

    public Customer(
            AppUser user, String firstname, String lastname,
            String address, String zipcode, String city
    ) {
        this.user = user;
        this.id = user.getId();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public CustomerDTO toCustomerResponseDTO() {
        return new CustomerDTO(
                id, firstname, lastname,
                address, zipcode, city, customerType
        );
    }
}
