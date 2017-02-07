package com.stefvar.udacityone.DAO.Movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stefvar.udacityone.DAO.Genres.GenreDAO;
import com.stefvar.udacityone.DAO.MovieDetails.CollectionDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieCreditsDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieImagesDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieKeywordsDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieListsDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieSimilarDAO;
import com.stefvar.udacityone.DAO.MovieDetails.ProductionCompanyDAO;
import com.stefvar.udacityone.DAO.MovieDetails.ProductionCountryDAO;
import com.stefvar.udacityone.DAO.MovieDetails.SpokenLanguageDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class MovieDetailsDAO {

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    private CollectionDAO belongsToCollection;
    @SerializedName("budget")
    @Expose
    private Integer budget;
    @SerializedName("genres")
    @Expose
    private List<GenreDAO> genres = new ArrayList<GenreDAO>();
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompanyDAO> productionCompanies = new ArrayList<ProductionCompanyDAO>();
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountryDAO> productionCountries = new ArrayList<ProductionCountryDAO>();
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("revenue")
    @Expose
    private Integer revenue;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguageDAO> spokenLanguages = new ArrayList<SpokenLanguageDAO>();
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("images")
    @Expose
    private MovieImagesDAO images;
    @SerializedName("keywords")
    @Expose
    private MovieKeywordsDAO keywords;
    @SerializedName("similar")
    @Expose
    private MovieSimilarDAO similar;
    @SerializedName("lists")
    @Expose
    private MovieListsDAO lists;
    @SerializedName("credits")
    @Expose
    private MovieCreditsDAO credits;

    /**
     *
     * @return
     * The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     *
     * @param adult
     * The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     *
     * @return
     * The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     *
     * @param backdropPath
     * The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     *
     * @return
     * The belongsToCollection
     */
    public CollectionDAO getBelongsToCollection() {
        return belongsToCollection;
    }

    /**
     *
     * @param belongsToCollection
     * The belongs_to_collection
     */
    public void setBelongsToCollection(CollectionDAO belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    /**
     *
     * @return
     * The budget
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     *
     * @param budget
     * The budget
     */
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    /**
     *
     * @return
     * The genres
     */
    public List<GenreDAO> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres
     * The genres
     */
    public void setGenres(List<GenreDAO> genres) {
        this.genres = genres;
    }

    /**
     *
     * @return
     * The homepage
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     *
     * @param homepage
     * The homepage
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

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
     * The imdbId
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     *
     * @param imdbId
     * The imdb_id
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     *
     * @return
     * The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     *
     * @param originalLanguage
     * The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    /**
     *
     * @return
     * The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     *
     * @param originalTitle
     * The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     *
     * @return
     * The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     *
     * @param overview
     * The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     *
     * @return
     * The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     * The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
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

    /**
     *
     * @return
     * The productionCompanies
     */
    public List<ProductionCompanyDAO> getProductionCompanies() {
        return productionCompanies;
    }

    /**
     *
     * @param productionCompanies
     * The production_companies
     */
    public void setProductionCompanies(List<ProductionCompanyDAO> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    /**
     *
     * @return
     * The productionCountries
     */
    public List<ProductionCountryDAO> getProductionCountries() {
        return productionCountries;
    }

    /**
     *
     * @param productionCountries
     * The production_countries
     */
    public void setProductionCountries(List<ProductionCountryDAO> productionCountries) {
        this.productionCountries = productionCountries;
    }

    /**
     *
     * @return
     * The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     *
     * @param releaseDate
     * The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     *
     * @return
     * The revenue
     */
    public Integer getRevenue() {
        return revenue;
    }

    /**
     *
     * @param revenue
     * The revenue
     */
    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    /**
     *
     * @return
     * The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     *
     * @param runtime
     * The runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     *
     * @return
     * The spokenLanguages
     */
    public List<SpokenLanguageDAO> getSpokenLanguages() {
        return spokenLanguages;
    }

    /**
     *
     * @param spokenLanguages
     * The spoken_languages
     */
    public void setSpokenLanguages(List<SpokenLanguageDAO> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     *
     * @param tagline
     * The tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     *
     * @param video
     * The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }

    /**
     *
     * @return
     * The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    /**
     *
     * @param voteAverage
     * The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    /**
     *
     * @return
     * The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     *
     * @param voteCount
     * The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     *
     * @return
     * The images
     */
    public MovieImagesDAO getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(MovieImagesDAO images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The keywords
     */
    public MovieKeywordsDAO getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(MovieKeywordsDAO keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The similar
     */
    public MovieSimilarDAO getSimilar() {
        return similar;
    }

    /**
     *
     * @param similar
     * The similar
     */
    public void setSimilar(MovieSimilarDAO similar) {
        this.similar = similar;
    }

    /**
     *
     * @return
     * The lists
     */
    public MovieListsDAO getLists() {
        return lists;
    }

    /**
     *
     * @param lists
     * The lists
     */
    public void setLists(MovieListsDAO lists) {
        this.lists = lists;
    }


    /**
     *
     * @return
     * The credits
     */
    public MovieCreditsDAO getCredits() {
        return credits;
    }

    /**
     *
     * @param credits
     * The credits
     */
    public void setCredits(MovieCreditsDAO credits) {
        this.credits = credits;
    }
}
