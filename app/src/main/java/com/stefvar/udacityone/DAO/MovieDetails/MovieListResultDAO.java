package com.stefvar.udacityone.DAO.MovieDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by steve62742 on 16/7/2016.
 */
public class MovieListResultDAO {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("favorite_count")
    @Expose
    private Integer favoriteCount;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("item_count")
    @Expose
    private Integer itemCount;
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The favoriteCount
     */
    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    /**
     *
     * @param favoriteCount
     * The favorite_count
     */
    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The itemCount
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     *
     * @param itemCount
     * The item_count
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     *
     * @return
     * The iso6391
     */
    public String getIso6391() {
        return iso6391;
    }

    /**
     *
     * @param iso6391
     * The iso_639_1
     */
    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     *
     * @param posterPath
     * The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
