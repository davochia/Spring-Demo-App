package com.example.Customer.service.serviceImpl;


import com.example.Customer.dto.CustomerDto;
import com.example.Customer.exception.CustomerNotFoundException;
import com.example.Customer.model.Customer;
import com.example.Customer.repository.CustomerRepository;
import com.example.Customer.service.CustomerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        if (customerDto == null)return null;
        Customer customer = CustomerDto.getAuthor(customerDto);
        return CustomerDto.getCustomerDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto findCustomerById(Integer id)  throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(CustomerDto::getCustomerDto).orElseThrow(() -> new CustomerNotFoundException(id));
    }


    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
        customers.forEach(customer -> customerDtos.add(CustomerDto.getCustomerDto(customer)));

        return customerDtos;
    }


    @Override
    public CustomerDto modifyCustomer(Integer id, CustomerDto customerDto)  {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(!optionalCustomer.isPresent())return null;

        Customer customer = optionalCustomer.get();

        if(customerDto == null) return null;

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setDataOfBirth(customerDto.getDateOfBirth());

        return CustomerDto.getCustomerDto(customerRepository.save(customer));
    }


    @Override
    public Boolean removeCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent())return false;

        Customer customer = optionalCustomer.get();
        customerRepository.delete(customer);

        return true;
    }

    /**
     * @param bookId Book ID
     * @param customerId Customer ID
     */
    @Override
    public void borrowBook(Integer bookId, Integer customerId) {
//        User user = getUserService.getById(userId);
//        if(user.getBorrowedBooks().stream()
//                .anyMatch(book -> book.getId()== bookId)){
//            throw new IllegalStateException("User already borrowed " +
//                    "the book");
//        }
//        if(user.getBorrowedBooks().size() >= 3){
//            throw new IllegalStateException("User already has " +
//                    "maximum number of books borrowed!");
//        }
//        Book book =
//                bookRepository.findById(bookId)
//                        .orElseThrow(() -> new EntityNotFoundException());
//
//        if(book.getNumberOfInstances()-1 < 0){
//            throw new IllegalStateException("There are no available" +
//                    " books!");
//        }
//        book.getUsers().add(user);
//        book.numberOfInstances(book.getNumberOfInstances()+1);
//        bookRepository.save(book);

    }
}
