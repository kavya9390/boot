package com.example.springboot.service;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAll();

    public void saveCustomer(Customer customer);

    public Customer getCustomer(int id);

    public void deleteCustomer(int id);

    public List<Ticket> findTicketsByCustomerId(int id);
}
