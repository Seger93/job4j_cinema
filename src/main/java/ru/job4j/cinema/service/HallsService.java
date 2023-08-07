package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Halls;

import java.util.Optional;

interface HallsService {
    Optional<Halls> getById(int id);
}
