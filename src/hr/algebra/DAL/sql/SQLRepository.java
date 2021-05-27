/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.DAL.sql;

import hr.algebra.DAL.Repository;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Film;
import hr.algebra.model.Genre;
import hr.algebra.model.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;


public class SQLRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String RUN_TIME = "Duration";
    private static final String PICTURE_PATH = "Cover";

    private static final String CREATE_MOVIE = "{ CALL MovieCreate (?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL MovieUpdate (?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL MovieDelete (?) }";
    private static final String DELETE_MOVIES = "{ CALL MoviesDelete }";
    private static final String SELECT_MOVIE = "{ CALL MovieSelect (?) }";
    private static final String SELECT_MOVIES = "{ CALL MoviesSelect }";
    private static final String CREATE_PERSONS = "{ CALL PersonCreate (?,?,?,?) }";
    private static final String SELECT_DIRECTORS = "{ CALL DirectorsSelect }";
    private static final String SELECT_ACTORS = "{ CALL ActorsSelect }";
    private static final String CREATE_GENRES = "{ CALL GenreCreate (?,?) }";
    private static final String SELECT_GENRES = "{ CALL GenresSelect }";
    private static final String CREATE_MOVIE_PERSON = "{ CALL PersonMovieCreate (?,?,?) }";
    private static final String DELETE_FILM_DJELATNIK = "{ CALL PersonMovieDelete }";
    private static final String CREATE_MOVIE_ZANR = "{ CALL GenreToMovieCreate (?,?) }";
    private static final String DELETE_FILM_GENRE = "{ CALL MovieGenreDelete }";
    private static final String DELETE_SELECTED_PERSON = "{ CALL DeletePerson (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL selectAllDirectors (?) }";
    private static final String SELECT_MOVIE_GENRE = "{ CALL SelectGenreMovie (?) }";
    private static final String DELETE_ALL = "{ CALL DeleteAll }";
    private static final String SELECT_ACTOR = "{ CALL SelectActor (?) }";
    private static final String DELETE_ALL_PERSONS = "{ CALL DeleteAllPersons }";
    
    

    @Override
    public int createMovie(Film film) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setInt(3, film.getRunTime());
            stmt.setString(4, film.getPicturePath());
            stmt.registerOutParameter(5, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(5);
        }
    }

    @Override
    public void createMovies(List<Film> films) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Film film : films) {
                stmt.setString(1, film.getTitle());
                stmt.setString(2, film.getDescription());
                stmt.setInt(3, film.getRunTime());
                stmt.setString(4, film.getPicturePath());
                stmt.registerOutParameter(5, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateMovie(int id, Film data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

            stmt.setString(1, data.getTitle());
            stmt.setString(2, data.getDescription());
            stmt.setInt(3, data.getRunTime());
            stmt.setString(4, data.getPicturePath());
            stmt.setInt(5, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Film> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Film(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(DESCRIPTION),
                            rs.getInt(RUN_TIME),
                            rs.getString(PICTURE_PATH)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Film> selectMovies() throws Exception {
        List<Film> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Film(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(DESCRIPTION),
                        rs.getInt(RUN_TIME),
                        rs.getString(PICTURE_PATH)
                ));
            }
        }

        return movies;
    }

    @Override
    public void deleteMovies() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIES)) {

            stmt.executeUpdate();
        }
    }

    @Override
    public void createDirectors(List<Director> directors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSONS)) {

            for (Director director : directors) {
                stmt.setString(1, director.getFirstName());
                stmt.setString(2, director.getLastName());
                stmt.setInt(3, 2);
                stmt.registerOutParameter(4, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public List<Director> selectDirectors() throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                directors.add(new Director(
                        rs.getInt(ID_OSOBA),
                        rs.getString(IME),
                        rs.getString(PREZIME),
                        rs.getInt(TIP_ID)
                ));
            }
        }
        return directors;
    }
    private static final String TIP_ID = "JobID";
    private static final String PREZIME = "LastName";
    private static final String IME = "FirstName";
    private static final String ID_OSOBA = "IDPerson";

    @Override
    public void createActors(List<Actor> actors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSONS)) {

            for (Actor actor : actors) {
                stmt.setString(1, actor.getFirstName());
                stmt.setString(2, actor.getLastName());
                stmt.setInt(3, 1);
                stmt.registerOutParameter(4, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public List<Actor> selectActors() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt(ID_OSOBA),
                        rs.getString(IME),
                        rs.getString(PREZIME),
                        rs.getInt(TIP_ID)
                ));
            }
        }
        return actors;
    }

    @Override
    public void createGenres(List<Genre> genres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRES)) {

            for (Genre zanr : genres) {
                stmt.setString(1, zanr.getName());
                stmt.registerOutParameter(2, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public List<Genre> selectGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt("IDGenre"),
                        rs.getString("Name")
                ));
            }
        }
        return genres;
    }

    
    
    @Override
    public void createMoviesDirectors(List<Film> movies, List<Director> directors, List<Director> directorsFromSQL, List<Film> moviesFromSQL) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON)) {
            Director dir = new Director();
            Film movie = new Film();
            for (int i = 0; i < movies.size(); i++) {
                for (int j = 0; j < directors.size(); j++) {
                    if (movies.get(i).getDirector().contains(directors.get(j))) {
                        for (Director director : directorsFromSQL) {
                            for (Film film : moviesFromSQL) {
                                if (directors.get(j).firstName.equals(director.firstName) && directors.get(j).lastName.equals(director.lastName) && movies.get(i).getTitle().equals(film.getTitle())) {
                                    dir = director;
                                    movie = film;

                                    stmt.setInt(1, movie.getId());
                                    stmt.setInt(2, dir.getId());
                                    stmt.registerOutParameter(3, Types.INTEGER);

                                    stmt.executeUpdate();
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    @Override
    public List<Director> selectDirector(Film movie) throws Exception {
        try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR);
            stmt.setInt(1, movie.getId());
            ResultSet result = stmt.executeQuery();
            List<Director> directors = new ArrayList<>();

            while (result.next()) {
                directors.add(new Director(result.getString(IME), result.getString(PREZIME)));
            }
            return directors;
        } catch (SQLException sQLException) {
            System.out.println("Slect Director ERROR");
            return null;
        }
    }

    @Override
    public void createMovieActors(List<Film> movies, List<Actor> actors, List<Actor> actorsFromSQL, List<Film> moviesFromSQL) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON)) {
            for (int i = 0; i < movies.size(); i++) {

                for (int j = 0; j < actors.size(); j++) {

                    if (movies.get(i).getActors().contains(actors.get(j))) {
                        for (Actor actor : actorsFromSQL) {
                            if (actors.get(j).firstName.equals(actor.firstName)) {
                                for (Film film : moviesFromSQL) {
                                    String[] str = film.getTitle().split(" ");
                                    if (movies.get(i).getTitle().contains(str[0])) {
                                        stmt.setInt(1, film.getId());
                                        stmt.setInt(2, actor.getId());
                                        stmt.registerOutParameter(3, Types.INTEGER);

                                        stmt.executeUpdate();
                                    }

                                }

                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void deleteFilmDjelatnik() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_FILM_DJELATNIK)) {

            stmt.executeUpdate();
        }
    }
    
    @Override
    public void deleteAll() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL)) {

            stmt.executeUpdate();
        }
    }


    @Override
    public void createMovieGenre(List<Film> movies, List<Genre> genres,
            List<Film> moviesFromSQL, List<Genre> genreFromSQL) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ZANR)) {
            Genre gen = new Genre();
            Film movie = new Film();
            for (int i = 0; i < movies.size(); i++) {
                for (int j = 0; j < genres.size(); j++) {
                    if (movies.get(i).getGenre().contains(genres.get(j))) {
                        for (Genre genre : genreFromSQL) {
                            for (Film film : moviesFromSQL) {
                                if (genres.get(j).getName().equals(genre.getName()) && movies.get(i).getTitle().equals(film.getTitle())) {
                                    gen = genre;
                                    movie = film;

                                    stmt.setInt(1, movie.getId());
                                    stmt.setInt(2, gen.getId());
                                    //stmt.registerOutParameter(3, Types.INTEGER);

                                    stmt.executeUpdate();
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    public List<Genre> selectGenreInMovie(Film movie) throws Exception {
        try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("select distinct Name from Movie inner join Genre on Genre.IDGenre=GenreID where MovieID= " + movie.getId());
            ResultSet result = statement.executeQuery();
            List<Genre> genres = new ArrayList<>();

            while (result.next()) {
                genres.add(new Genre(result.getString(TITLE)));
            }
            return genres;
        } catch (SQLException sQLException) {
            System.out.println("Ne ide vise");
            return null;
        }
    }

    @Override
    public void deleteFilmGenre() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_FILM_GENRE)) {

            stmt.executeUpdate();
        }
    }

    @Override
    public void createNewActorMovie(Film film, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON)) {
            stmt.setInt(1, film.getId());
            stmt.setInt(2, actor.getId());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
        }
    }




    @Override
    public void createNewGenreMovie(Film film, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ZANR)) {
            stmt.setInt(1, film.getId());
            stmt.setInt(2, genre.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void createActor(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSONS)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.setInt(3, 1);
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();
        }

    }

    @Override
    public void createDirector(Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSONS)) {

            stmt.setString(1, director.getFirstName());
            stmt.setString(2, director.getLastName());
            stmt.setInt(3, 2);
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();
        }
    }

    

    @Override
    public List<Actor> selectActor(Film movie) throws Exception {
          try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();
            CallableStatement statement = con.prepareCall(SELECT_ACTOR);
            statement.setInt(1, movie.getId());
            ResultSet result = statement.executeQuery();
            List<Actor> actors = new ArrayList<>();
            
            
            while (result.next()) {
                actors.add(new Actor(result.getString(IME), result.getString(PREZIME)));
            }
            return actors;
        } catch (SQLException sQLException) {
            System.out.println("Select Actor ERROR!");
            return null;
        }
    }

    @Override
    public List<Genre> selectGenre(Film movie) throws Exception {
           List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_GENRE)) {
                
                stmt.setInt(1, movie.getId());
                
                ResultSet rs = stmt.executeQuery(); 
                
            while (rs.next()) {
                
                genres.add(new Genre(
                        rs.getInt("IDGenre"),
                        rs.getString("Name")
                ));
            }
        }
        return genres;
    }

    @Override
    public void deleteAllPersons() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL_PERSONS)) {
            stmt.executeUpdate();
        }   
    }
    
    @Override
    public void deletePerson(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_SELECTED_PERSON)) {
            stmt.setInt(1,person.id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void createNewActorMovie(Film film, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON)) {
            stmt.setInt(1, film.getId());
            stmt.setInt(2, director.getId());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
        }
    }

 
    
}
