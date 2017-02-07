package com.stefvar.udacityone.DAO.MovieDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 17/7/2016.
 */
public class MovieCreditsDAO {

    @SerializedName("cast")
    @Expose
    private List<MovieCastDAO> cast = new ArrayList<MovieCastDAO>();
    @SerializedName("crew")
    @Expose
    private List<MovieCrewDAO> crew = new ArrayList<MovieCrewDAO>();


    /**
     *
     * @return
     * The cast
     */
    public List<MovieCastDAO> getCast() {
        return cast;
    }

    /**
     *
     * @param cast
     * The cast
     */
    public void setCast(List<MovieCastDAO> cast) {
        this.cast = cast;
    }

    /**
     *
     * @return
     * The crew
     */
    public List<MovieCrewDAO> getCrew() {
        return crew;
    }

    /**
     *
     * @param crew
     * The crew
     */
    public void setCrew(List<MovieCrewDAO> crew) {
        this.crew = crew;
    }


        /*---*/

    public MovieCrewDAO getJob(String job) {

        for (int i=0; i<crew.size();i++){
            if (crew.get(i).getJob().equals(job)){
                return crew.get(i);
            }
        }
        return null;

    }


    /*---*/


}
