package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.FilmSessions;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionRepository {

    Optional<FilmSessions> getById(int id);

    Collection<FilmSessions> findAll();

    Collection<FilmSessions> getAllByFilmId(int filmId);
}