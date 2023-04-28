package com.example.springboot.entity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

    @Test
    public void testConstructor(){
        Customer customer = new Customer();
        assertNotNull(customer);
    }

    @Test
    public void testCustomerInitialization() {
        Customer customer = new Customer("John", "john@example.com");
        assertEquals("John", customer.getName());
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer("John", "john@example.com");
        assertEquals("Customer{id=0, name='John', email='john@example.com'}", customer.toString());
    }

    @Test
    public void testCustomerIdGeneration() {
        Customer customer = new Customer("John", "john@example.com");
        assertEquals(0, customer.getId());
    }

    @Test
    public void testId(){
        Customer customer = new Customer();
        customer.setId(1);
        assertEquals(1,customer.getId());
    }

    @Test
    public void testName(){
        Customer customer = new Customer();
        customer.setName("Kavya");
        assertEquals("Kavya",customer.getName());
    }

    @Test
    public void testEmail(){
        Customer customer = new Customer();
        customer.setEmail("kavya@gmail.com");
        assertEquals("kavya@gmail.com",customer.getEmail());
    }

    @Test
    public void testTickets(){
        Customer customer = new Customer();
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        customer.setTickets(ticketList);
        assertEquals(ticketList,customer.getTickets());
    }

    @Test
    public void testAddTicketMethod(){
        Customer customer = new Customer();
        Ticket ticket = new Ticket();
        customer.addTicket(ticket);
        assertTrue(customer.getTickets().contains(ticket));
    }
}

