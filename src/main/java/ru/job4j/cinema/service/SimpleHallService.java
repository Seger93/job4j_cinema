package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Halls;
import ru.job4j.cinema.repository.HallsRepository;

import java.util.Optional;

@Service
public class SimpleHallService implements HallsService {

    public final HallsRepository hallsRepository;

    public SimpleHallService(HallsRepository hallsRepository) {
        this.hallsRepository = hallsRepository;
    }

    @Override
    public Optional<Halls> getById(int id) {
        return hallsRepository.findById(id);
    }
}