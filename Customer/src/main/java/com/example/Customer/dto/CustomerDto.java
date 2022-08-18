package com.example.Customer.dto;


import com.example.Customer.model.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String email;
    private Date dateOfBirth;


    public static Customer getAuthor(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setDataOfBirth(customerDto.getDateOfBirth());

        return customer;
    }

    public static CustomerDto getCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        customerDto.setDateOfBirth(customer.getDataOfBirth());

        return customerDto;
    }

}
