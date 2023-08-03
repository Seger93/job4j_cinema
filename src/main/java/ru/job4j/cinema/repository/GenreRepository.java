package ru.job4j.cinema.repository;

import java.util.Optional;

public interface GenreRepository {

     Optional<String> getId(int id);
}