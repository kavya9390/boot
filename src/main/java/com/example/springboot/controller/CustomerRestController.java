package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.service.CustomerService;
import com.example.springboot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){

        Customer tempCustomer = customerService.getCustomer(customerId);

        if(tempCustomer == null)
            throw new RuntimeException("Customer not found" + customerId);

        return tempCustomer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        if(customerService.getCustomer(customerId) == null)
            throw new RuntimeException("Customer does not exist - " + customerId);
        customerService.deleteCustomer(customerId);
        return "Deleted customer - " + customerId;
    }

    @GetMapping("/tickets")
    public List<Ticket> getTickets(){
        return ticketService.findAll();
    }

    @GetMapping("/ticket/{ticketId}")
    public Ticket getTicket(@PathVariable int ticketId){

        Ticket tempTicket = ticketService.getTicket(ticketId);

        if(tempTicket == null)
            throw new RuntimeException("Ticket not found" + ticketId);

        return tempTicket;
    }


    @GetMapping("/tickets/{customerId}")
    public List<Ticket> getTicketsByCustomer(@PathVariable int customerId){
        return customerService.findTicketsByCustomerId(customerId);
    }


    @PostMapping("/saveTicket/{customerId}")
    public Ticket saveTicket(@PathVariable int customerId,@RequestBody Ticket ticket){
        ticketService.saveTicket(ticket,customerId);
        return ticket;
    }

    @DeleteMapping("/ticket/{ticketId}")
    public String deleteTicket(@PathVariable int ticketId){
        if(ticketService.getTicket(ticketId) == null)
            throw new RuntimeException("Ticket does not exist - " + ticketId);
        ticketService.deleteTicket(ticketId);
        return "Deleted Ticket - " + ticketId;
    }


}
