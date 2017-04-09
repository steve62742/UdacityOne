package com.stefvar.udacityone.DAO.MovieDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by steve62742 on 6/4/2017.
 */

public class MovieVideos {

    @SerializedName("results")
    @Expose
    private List<MovieVideo> results = null;

    public List<MovieVideo> getResults() {
        return results;
    }

    public void setResults(List<MovieVideo> results) {
        this.results = results;
    }

}
