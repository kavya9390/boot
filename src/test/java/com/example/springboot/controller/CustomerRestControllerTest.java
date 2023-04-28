package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.service.CustomerService;
import com.example.springboot.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRestControllerTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private CustomerRestController controller;

    @Test
    public void testGetCustomers(){
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("John","john@gmail.com"));
        customerList.add(new Customer("Smith","smith@gmail.com"));
        when(customerService.findAll()).thenReturn(customerList);
        List<Customer> customers = controller.getCustomers();
        assertEquals(customerList,customers);
        assertEquals(2,customers.size());
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionGetCustomerById(){
        controller.getCustomer(-1);
    }

    @Test
    public void testGetCustomerById(){
        Customer customer = new Customer("John","john@gmail.com");
        customer.setId(1);
        when(customerService.getCustomer(1)).thenReturn(customer);
        assertEquals(customer,controller.getCustomer(1));
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer( "John","john@gmail.com");

        controller.addCustomer(customer);

        verify(customerService, Mockito.times(1)).saveCustomer(customer);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer( "John","john@gmail.com");

        controller.updateCustomer(customer);

        verify(customerService, Mockito.times(1)).saveCustomer(customer);
    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer("John","john@gmail.com");
        customer.setId(1);
        when(customerService.getCustomer(1)).thenReturn(customer);

        controller.deleteCustomer(1);

        verify(customerService, Mockito.times(1)).deleteCustomer(1);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionDeleteCustomer(){
        controller.deleteCustomer(1);
    }

    @Test
    public void testGetTickets(){
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket("20B","1hr"));
        when(ticketService.findAll()).thenReturn(ticketList);
        List<Ticket> tickets = controller.getTickets();
        assertEquals(ticketList,tickets);
    }

    @Test
    public void testGetTicket(){
        Ticket ticket = new Ticket("20B","1hr");
        ticket.setId(201);
        when(ticketService.getTicket(201)).thenReturn(ticket);
        Ticket ticket1 = controller.getTicket(201);
        assertEquals(ticket,ticket1);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionGetTicket(){
        controller.getTicket(23);
    }

    @Test
    public void testDeleteTicket(){
        Ticket ticket = new Ticket("20B","1hr");
        ticket.setId(201);
        when(ticketService.getTicket(201)).thenReturn(ticket);
        controller.deleteTicket(201);
        verify(ticketService,times(1)).deleteTicket(201);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionDeleteTicket(){
        controller.deleteTicket(101);
    }

    @Test
    public void testBookTicket(){
        Customer customer = new Customer("John","john@gmail.com");
        customer.setId(1);
        when(customerService.getCustomer(1)).thenReturn(customer);

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket("20B","1hr"));
        when(customerService.findTicketsByCustomerId(1)).thenReturn(ticketList);
        List<Ticket> tickets = controller.getTicketsByCustomer(1);
        assertEquals(ticketList,tickets);

        Ticket newTicket = new Ticket("19C","2hrs");
        controller.saveTicket(1,newTicket);
        verify(ticketService,times(1)).saveTicket(newTicket,1);
    }
}
