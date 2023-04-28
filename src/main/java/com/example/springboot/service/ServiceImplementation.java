package com.example.springboot.service;

import com.example.springboot.entity.Ticket;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImplementation implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer = null;
        if(byId.isPresent())
            customer = byId.get();
        else
            throw new RuntimeException("Customer not found - " + id);
        return customer;
    }
    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Ticket> findTicketsByCustomerId(int id) {
        Optional<Customer> tempCustomer = customerRepository.findById(id);
        if(!tempCustomer.isPresent())
            throw  new RuntimeException("Customer does not exist - " + id);
        return tempCustomer.get().getTickets();
    }
}
