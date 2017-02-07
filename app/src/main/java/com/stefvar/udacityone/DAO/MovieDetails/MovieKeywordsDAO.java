package com.stefvar.udacityone.DAO.MovieDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class MovieKeywordsDAO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("keywords")
    @Expose
    private List<KeywordsDAO> keywords = new ArrayList<KeywordsDAO>();

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
     * The keywords
     */
    public List<KeywordsDAO> getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(List<KeywordsDAO> keywords) {
        this.keywords = keywords;
    }

}
