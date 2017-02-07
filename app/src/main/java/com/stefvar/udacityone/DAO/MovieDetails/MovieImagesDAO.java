package com.stefvar.udacityone.DAO.MovieDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class MovieImagesDAO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<BackdropDAO> backdrops = new ArrayList<BackdropDAO>();
    @SerializedName("posters")
    @Expose
    private List<PosterDAO> posters = new ArrayList<PosterDAO>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The backdrops
     */
    public List<BackdropDAO> getBackdrops() {
        return backdrops;
    }

    /**
     *
     * @param backdrops
     * The backdrops
     */
    public void setBackdrops(List<BackdropDAO> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     *
     * @return
     * The posters
     */
    public List<PosterDAO> getPosters() {
        return posters;
    }

    /**
     *
     * @param posters
     * The posters
     */
    public void setPosters(List<PosterDAO> posters) {
        this.posters = posters;
    }

}
