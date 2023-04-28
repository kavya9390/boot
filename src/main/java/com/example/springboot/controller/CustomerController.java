package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Ticket;
import com.example.springboot.service.CustomerService;
import com.example.springboot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
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

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customers/list";
    }

    @GetMapping("/tickets/{customerId}")
    public String getTickets(@PathVariable("customerId") int id,Model model){
        List<Ticket> ticketList = customerService.findTicketsByCustomerId(id);
        model.addAttribute("tickets",ticketList);
        model.addAttribute("customerId",id);
        return "list-tickets";
    }


    @GetMapping("/showFormToBook")
    public String showFormToBookTicket(@RequestParam("customerId") int id, Model theModel) {

        Ticket ticket = new Ticket();
        theModel.addAttribute("ticket", ticket);
        theModel.addAttribute("customerId",id);

        return "ticket-form";
    }

    @PostMapping("/saveTicket/{customerId}")
    public String saveTicket(@PathVariable("customerId") int id,@ModelAttribute("ticket") Ticket ticket){
        ticketService.saveTicket(ticket,id);
        return "redirect:/customers/tickets/" + id;
    }

    @GetMapping("/{customerId}/ticketDelete/{ticketId}")
    public String ticketDelete(@PathVariable("ticketId") int theId,@PathVariable("customerId") int customerId) {
        ticketService.deleteTicket(theId);
        return "redirect:/customers/tickets/" + customerId;
    }
}
