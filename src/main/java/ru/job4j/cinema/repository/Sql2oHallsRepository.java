package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Halls;

import java.util.Optional;
@Repository
public class Sql2oHallsRepository implements HallsRepository {

    private final Sql2o sql2o;

    public Sql2oHallsRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Halls> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT id, name, row_count, place_count, description " +
                            "FROM halls " +
                            "WHERE id = :id");
            query.addParameter("id", id);
            var halls = query.setColumnMappings(Halls.COLUMN_MAPPING).executeAndFetchFirst(Halls.class);
            return Optional.ofNullable(halls);
        }
    }
}