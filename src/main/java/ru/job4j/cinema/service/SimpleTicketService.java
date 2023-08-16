package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Tickets;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

@Service
public class SimpleTicketService implements TicketService {

    private  final TicketRepository ticketRepository;

    public SimpleTicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Tickets> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Optional<Tickets> save(Tickets ticket) {
        return ticketRepository.save(ticket);
    }
}