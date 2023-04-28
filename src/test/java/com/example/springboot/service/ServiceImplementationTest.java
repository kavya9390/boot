package com.example.springboot.service;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.repository.CustomerRepository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceImplementationTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ServiceImplementation serviceImplementation;

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Customer customer = new Customer("John", "Doe");
        List<Customer> customers = Arrays.asList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> actualCustomers = serviceImplementation.findAll();

        assertEquals(customers, actualCustomers);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer("John", "Doe");
        serviceImplementation.saveCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testGetCustomer() {
        Customer customer = new Customer("John", "Doe");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        Customer actualCustomer = serviceImplementation.getCustomer(1);
        assertEquals(customer, actualCustomer);
        verify(customerRepository, times(1)).findById(1);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCustomerNotFound() {
        serviceImplementation.getCustomer(-1);
    }

    @Test
    public void testDeleteCustomer() {
        serviceImplementation.deleteCustomer(1);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    public void testFindTicketById() {
        Customer customer = new Customer("John", "Doe");
        Ticket ticket = new Ticket("20B","2hrs");
        customer.setTickets(Arrays.asList(ticket));
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        List<Ticket> actualTickets = serviceImplementation.findTicketsByCustomerId(1);
        assertEquals(Arrays.asList(ticket), actualTickets);
        verify(customerRepository, times(1)).findById(1);
    }

    @Test(expected = RuntimeException.class)
    public  void testExceptionFindTicketById(){
        serviceImplementation.findTicketsByCustomerId(-1);
    }
}
