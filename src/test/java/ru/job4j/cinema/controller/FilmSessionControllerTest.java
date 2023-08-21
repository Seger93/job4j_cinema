package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.DtoFilmSession;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSessions;
import ru.job4j.cinema.model.Halls;
import ru.job4j.cinema.service.FilmSessionService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmSessionControllerTest {

    private FilmSessionController filmSessionController;

    private FilmSessionService filmSessionService;

    @BeforeEach
    public void initServices() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    @Test
    public void whenGetFindAll() {
        LocalDateTime start = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2023, 7, 1, 12, 15);
        FilmSessions filmSessions = new FilmSessions(0, 1, 1, start, end, 100);
        FilmSessions filmSessions1 = new FilmSessions(1, 2, 2, start, end, 300);
        Film film = new Film(0, "name0", "film0", 2000, 1, 18, 45, 1);
        Halls halls = new Halls(filmSessions.getHallsId(), " name", 1, 2, "Description");
        Halls halls1 = new Halls(filmSessions1.getHallsId(), " name1", 2, 3, "Description1");
        DtoFilmSession dtoFilmSession = new DtoFilmSession(film, halls, filmSessions);
        DtoFilmSession dtoFilmSession1 = new DtoFilmSession(film, halls1, filmSessions1);
        Collection<DtoFilmSession> expected = List.of(dtoFilmSession, dtoFilmSession1);
        when(filmSessionService.findAll()).thenReturn(expected);

        ConcurrentModel concurrentModel = new ConcurrentModel();
        String view = filmSessionController.getAll(concurrentModel);
        Object actual = concurrentModel.getAttribute("sessions");

        assertThat(view).isEqualTo("sessions/list");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void whenGetById() {
        LocalDateTime start = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2023, 7, 1, 12, 15);
        FilmSessions filmSessions = new FilmSessions(0, 1, 1, start, end, 100);
        FilmSessions filmSessions1 = new FilmSessions(1, 2, 2, start, end, 300);
        Film film = new Film(filmSessions1.getFilmId(), "name0", "film0", 2000, 1, 18, 45, 1);
        Halls halls = new Halls(filmSessions.getHallsId(), " name", 1, 2, "Description");
        Halls halls1 = new Halls(filmSessions1.getHallsId(), " name1", 2, 3, "Description1");
        DtoFilmSession dtoFilmSession = new DtoFilmSession(film, halls, filmSessions);
        DtoFilmSession dtoFilmSession1 = new DtoFilmSession(film, halls1, filmSessions1);
        Collection<DtoFilmSession> expected = List.of(dtoFilmSession, dtoFilmSession1);
        when(filmSessionService.getAllByFilmId(1)).thenReturn(expected);

        ConcurrentModel concurrentModel = new ConcurrentModel();
        String view = filmSessionController.findById(concurrentModel, 1);
        Object actual = concurrentModel.getAttribute("sessions");

        assertThat(view).isEqualTo("sessions/one");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}