package com.example.springboot.service;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpementation implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void saveTicket(Ticket ticket,int id) {
        Optional<Customer> tempCustomer = customerRepository.findById(id);
        if(!tempCustomer.isPresent())
            throw new RuntimeException("Customer does not exist - " + id);
        ticket.setCustomer(tempCustomer.get());
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(int id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        Ticket tempTicket = null;
        if(byId.isPresent())
            tempTicket = byId.get();
        else
            throw new RuntimeException("Ticket not found - " + id);
        return tempTicket;
    }

    @Override
    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }

}
