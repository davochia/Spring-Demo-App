package com.example.Customer.controller;

import com.example.Customer.dto.CustomerDto;
import com.example.Customer.exception.CustomerNotFoundException;
import com.example.Customer.service.serviceImpl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;


    @GetMapping
    @Operation(summary="Get all Customers from system")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @Operation(summary="Get Customer from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@Parameter(description = "id of Customer to be searched") @PathVariable Integer id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary="Add a new Customer to system")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.addCustomer(customerDto) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(summary="Edit Customer in the system")
    public ResponseEntity<CustomerDto> editCustomer(@PathVariable Integer id, @RequestBody CustomerDto newCustomer) {
        return new ResponseEntity<>(customerService.modifyCustomer(id, newCustomer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete Customer from system")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        customerService.removeCustomer(id);
        return new ResponseEntity<>("Customer with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }


//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Customer Found",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = CustomerDto.class)) }),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "Customer not found",
//                    content = @Content) })
//    @Operation(summary="Get Customer from system find by id")
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerDto> borrowBook(@Parameter(description = "id of Customer to be searched") @PathVariable Integer id) throws CustomerNotFoundException {
//        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
//    }

}
