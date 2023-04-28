package com.example.springboot.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {

    @Test
    public void testConstructor(){
        Ticket ticket = new Ticket();
        assertNotNull(ticket);
    }

    @Test
    public void testInitializer(){
        Ticket ticket = new Ticket("20A","2hrs");
        assertEquals("20A",ticket.getSeatName());
        assertEquals("2hrs",ticket.getDuration());
    }

    @Test
    public void testToString(){
        Ticket ticket = new Ticket("20A","2hrs");
        assertEquals("Ticket{id=0, seatName='20A', duration='2hrs', customer=null}",ticket.toString());
    }

    @Test
    public void testId(){
        Ticket ticket = new Ticket();
        ticket.setId(1);
        assertEquals(1,ticket.getId());
    }

    @Test
    public void testSeatName(){
        Ticket ticket = new Ticket();
        ticket.setSeatName("20C");
        assertEquals("20C",ticket.getSeatName());
    }

    @Test
    public void testDuration(){
        Ticket ticket = new Ticket();
        ticket.setDuration("2hrs");
        assertEquals("2hrs",ticket.getDuration());
    }

    @Test
    public void testCustomer(){
        Ticket ticket = new Ticket();
        Customer customer = new Customer();
        ticket.setCustomer(customer);
        assertEquals(customer,ticket.getCustomer());
    }


}
