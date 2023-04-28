package com.example.springboot.service;

import com.example.springboot.entity.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> findAll();

    public void saveTicket(Ticket ticket,int id);

    public Ticket getTicket(int id);

    public void deleteTicket(int id);
}

