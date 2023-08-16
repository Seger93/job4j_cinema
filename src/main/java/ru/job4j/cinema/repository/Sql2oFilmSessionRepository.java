package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.FilmSessions;

import java.util.Collection;
import java.util.Optional;
@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<FilmSessions> getById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
           var filmSessions = query.setColumnMappings(FilmSessions.COLUMN_MAPPING).
                   executeAndFetchFirst(FilmSessions.class);
        return Optional.ofNullable(filmSessions);
        }
    }

    @Override
    public Collection<FilmSessions> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(FilmSessions.COLUMN_MAPPING).executeAndFetch(FilmSessions.class);
        }
    }

    @Override
    public Collection<FilmSessions> getAllByFilmId(int filmId) {
        try (var connection = sql2o.open()) {
            String str = """
                    SELECT * FROM film_sessions
                    WHERE film_id = :film_id
                    """;
            var query = connection.createQuery(str);
            query.addParameter("film_id", filmId);
            return query.setColumnMappings(FilmSessions.COLUMN_MAPPING)
                    .executeAndFetch(FilmSessions.class);
        }
    }
}