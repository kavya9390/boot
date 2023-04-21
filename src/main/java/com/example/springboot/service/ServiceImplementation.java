package com.example.springboot.service;

import com.example.springboot.entity.Ticket;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.entity.Customer;
import com.example.springboot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImplementation implements CustomerService{
    private CustomerRepository customerRepository;

    public ServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    private TicketRepository ticketRepository;

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

    public void createTicket(int customerId, Ticket ticket) {
        Customer customer = getCustomer(customerId);
        ticket.setCustomer(customer);
        ticketRepository.save(ticket);
    }
}
