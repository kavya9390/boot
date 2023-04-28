package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.service.CustomerService;
import com.example.springboot.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private TicketService ticketService;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testListEmployees() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        when(customerService.findAll()).thenReturn(customers);
        String view = customerController.listEmployees(model);
        assertEquals("list-customers", view);
        verify(customerService, times(1)).findAll();
    }

    @Test
    public void testShowFormForAdd() {
        String view = customerController.showFormForAdd(model);
        assertEquals("customer-form", view);
        verify(model, times(1)).addAttribute(eq("customer"), any(Customer.class));
    }

    @Test
    public void testShowFormForUpdate() {
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerService.getCustomer(customerId)).thenReturn(customer);
        String view = customerController.showFormForUpdate(customerId, model);
        assertEquals("customer-form", view);
        verify(customerService, times(1)).getCustomer(customerId);
        verify(model, times(1)).addAttribute("customer", customer);
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        String view = customerController.saveCustomer(customer);
        assertEquals("redirect:/customers/list", view);
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void testDelete() {
        int customerId = 1;
        String view = customerController.delete(customerId);
        assertEquals("redirect:/customers/list", view);
        verify(customerService, times(1)).deleteCustomer(customerId);
    }

    @Test
    public void testShowFormToBook(){
        Model model = mock(Model.class);
        String view = customerController.showFormToBookTicket(1,model);
        assertEquals("ticket-form",view);
    }

    @Test
    public void testGetTickets(){
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        when(customerService.findTicketsByCustomerId(1)).thenReturn(ticketList);
        Model model = mock(Model.class);
        String view  = customerController.getTickets(1,model);
        verify(customerService).findTicketsByCustomerId(1);
        verify(model).addAttribute("tickets",ticketList);
        assertEquals("list-tickets",view);
    }

    @Test
    public void testSaveTicket(){
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setSeatName("20D");
        ticket.setDuration("2hrs");
        ticket.setCustomer(new Customer("John","john@gmail.com"));

        String view = customerController.saveTicket(1,ticket);
        verify(ticketService).saveTicket(ticket,1);
        assertEquals("redirect:/customers/tickets/1",view);

    }

    @Test
    public void testDeleteTicket(){
        String view = customerController.ticketDelete(201,1);
        assertEquals("redirect:/customers/tickets/1",view);
        verify(ticketService,times(1)).deleteTicket(201);
    }
}
