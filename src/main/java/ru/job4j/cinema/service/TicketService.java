package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Tickets;

import java.util.Optional;

public interface TicketService {
    Optional<Tickets> findById(int id);

    Optional<Tickets> save(Tickets ticket);
}