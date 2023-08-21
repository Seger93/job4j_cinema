package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.service.FilmService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmsControllerTest {

    private FilmService filmService;
    private FilmsController filmsController;

    @BeforeEach
    public void initServices() {
        filmService = mock(FilmService.class);
        filmsController = new FilmsController(filmService);
    }

    @Test
    public void whenGetFilmId() {
        Film film = new Film(0, "name0", "film0", 2000, 1, 18, 45, 1);
        Film film1 = new Film(0, "name1", "film1", 2001, 2, 18, 45, 2);
        Collection<Film> exp = List.of(film, film1);
        when(filmService.findAll()).thenReturn(exp);

        ConcurrentModel model = new ConcurrentModel();
        String view = filmsController.getAll(model);
        Object actualFilms = model.getAttribute("films");

        assertThat(view).isEqualTo("films/list");
        assertThat(actualFilms).usingRecursiveComparison().isEqualTo(exp);
    }

    @Test
    public void whenGetAllFilms() {
        Film expected = new Film(1, "name1", "description1", 2001, 1, 16, 100, 1);
        when(filmService.findById(1)).thenReturn(Optional.of(expected));

        ConcurrentModel model = new ConcurrentModel();
        String view = filmsController.findById(model, 1);
        Object actualFilm = model.getAttribute("films");

        assertThat(view).isEqualTo("films/one");
        assertThat(actualFilm).usingRecursiveComparison().isEqualTo(expected);
    }
}