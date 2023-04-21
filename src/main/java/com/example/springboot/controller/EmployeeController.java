package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.service.CustomerService;
import com.example.springboot.service.TicketService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class EmployeeController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/list")
    public String listEmployees(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers",customerList);
      return "list-customers";
    }

    @GetMapping("/tickets")
    public String getTickets(Model model){
        List<Ticket> ticketList = ticketService.findAll();
        model.addAttribute("tickets",ticketList);
        return "list-tickets";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Customer tempCustomer = new Customer();

        theModel.addAttribute("customer", tempCustomer);

        return "customer-form";
    }

     @GetMapping("/showFormForUpdate")
     public String showFormForUpdate(@RequestParam("customerId") int theId,
                                     Model theModel) {

         Customer tempCustomer = customerService.getCustomer(theId);

         theModel.addAttribute("customer", tempCustomer);
         return "customer-form";
     }


    @GetMapping("/showFormToBook")
    public String showFormToBookTicket(@RequestParam("customerId") int id, Model theModel) {

        Ticket ticket = new Ticket();

        Customer tempCustomer = customerService.getCustomer(id);
        tempCustomer.addTicket(ticket);
        theModel.addAttribute("ticket", ticket);
        theModel.addAttribute("customer",tempCustomer);

        return "ticket-form";
    }

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket){
        ticketService.saveTicket(ticket);
        return "redirect:/customers/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customers/list";

    }
}
