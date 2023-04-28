package com.example.springboot.service;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.repository.TicketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceImplementationTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpementation ticketServiceImpementation;

    @Test
    public void testFindAll(){
        List<Ticket> ticketList = new ArrayList<>();
        when(ticketRepository.findAll()).thenReturn(ticketList);
        List<Ticket> ticketsReturned = ticketServiceImpementation.findAll();
        assertEquals(ticketList,ticketsReturned);
    }

    @Test
    public void testSaveTicket(){
        Customer customer = new Customer();
        customer.setId(1);
        Ticket ticket = new Ticket();
        ticket.setId(1);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        ticketServiceImpementation.saveTicket(ticket,1);
        verify(ticketRepository).save(ticket);
        assertEquals(customer,ticket.getCustomer());
    }

    @Test
    public void testGetTicket(){
        int ticketId = 2;
        Ticket ticket = new Ticket();
        ticket.setId(2);
        when(ticketRepository.findById(2)).thenReturn(Optional.of(ticket));
        Ticket tempTicket = ticketServiceImpementation.getTicket(ticketId);
        assertEquals(ticket,tempTicket);
    }

    @Test(expected = RuntimeException.class)
    public void testGetException(){
        ticketServiceImpementation.getTicket(-1);
    }

    @Test
    public void testDeleteTicket() {
        ticketServiceImpementation.deleteTicket(1);
        verify(ticketRepository).deleteById(1);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionSaveTicket(){
        Ticket ticket = new Ticket();
        ticketServiceImpementation.saveTicket(ticket,123);
    }

}
