package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.FilmSessions;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Halls;

import java.time.LocalDateTime;
import java.util.Objects;

public class DtoFilmSession {
    private int id;

    private Film film;

    private Halls hall;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    public DtoFilmSession(Film film, Halls hall, FilmSessions filmSession) {
        this.id = filmSession.getId();
        this.film = film;
        this.hall = hall;
        this.startTime = LocalDateTime.from(filmSession.getStartTime());
        this.endTime = LocalDateTime.from(filmSession.getEndTime());
        this.price = filmSession.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Halls getHall() {
        return hall;
    }

    public void setHall(Halls hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DtoFilmSession that = (DtoFilmSession) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}