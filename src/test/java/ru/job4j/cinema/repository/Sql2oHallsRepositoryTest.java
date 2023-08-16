package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Halls;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oHallsRepositoryTest {

    private static Sql2oHallsRepository sql2oHallsRepository;

    @BeforeAll
    public static void initRepository() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = Sql2oHallsRepositoryTest.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        String url = properties.getProperty("datasource.url");
        String username = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");

        DatasourceConfiguration configuration = new DatasourceConfiguration();
        DataSource datasource = configuration.connectionPool(url, username, password);
        Sql2o sql2o = configuration.databaseClient(datasource);

        sql2oHallsRepository = new Sql2oHallsRepository(sql2o);
    }

    @Test
    public void whenHallsGetId() {
        var exp = new Halls(1, "Зал 1", 10, 10, "Зал средних размеров");
        assertThat(sql2oHallsRepository.findById(1).get()).isEqualTo(exp);
    }

    @Test
    public void whenDidNotFindById() {
        Optional<Halls> actualHall = sql2oHallsRepository.findById(10);
        assertThat(actualHall).isEmpty();
    }
}