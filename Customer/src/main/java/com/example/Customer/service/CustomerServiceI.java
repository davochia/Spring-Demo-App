package com.example.Customer.service;

import com.example.Customer.dto.CustomerDto;
import com.example.Customer.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceI {
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto findCustomerById(Integer id) throws CustomerNotFoundException;
    List<CustomerDto> getCustomers( );
    CustomerDto modifyCustomer(Integer id, CustomerDto customerDto);
    Boolean removeCustomer(Integer id) ;

    void borrowBook(Integer bookId, Integer customerId);
}
