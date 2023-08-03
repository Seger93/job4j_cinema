package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Tickets;

import java.util.Optional;

public interface TicketRepository {

    Optional<Tickets> findById(int id);

    Optional<Tickets> save(Tickets ticket);
}