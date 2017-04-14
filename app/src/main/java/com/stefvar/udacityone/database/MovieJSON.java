package com.stefvar.udacityone.database;

import com.google.gson.Gson;
import com.stefvar.udacityone.DAO.Movies.FocusMovieDAO;

/**
 * Created by steve62742 on 11/4/2017.
 */

public class MovieJSON {
    private long id;
    private String movieJSON;
    Gson gson = new Gson();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getmovieJSON() {
        return movieJSON;
    }

    public void setmovieJSON(String movieJSON) {
        this.movieJSON = movieJSON;
    }

    public void setMovie(FocusMovieDAO movieDAO){
        this.movieJSON = gson.toJson(movieDAO);
    }

    public FocusMovieDAO getMovie (){
        return gson.fromJson(this.movieJSON, FocusMovieDAO.class);
    }

}
