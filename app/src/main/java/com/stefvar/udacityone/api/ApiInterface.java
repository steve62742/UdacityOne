package com.stefvar.udacityone.api;


import com.stefvar.udacityone.DAO.Genres.GenresDAO;
import com.stefvar.udacityone.DAO.MovieDetails.CollectionDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieImagesDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieKeywordsDAO;
import com.stefvar.udacityone.DAO.Movies.MovieDetailsDAO;
import com.stefvar.udacityone.DAO.Movies.MoviesDAO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by steve62742 on 18/6/2016.
 */
public interface ApiInterface {

    @GET("3/movie/{i}")
    Call<MoviesDAO> getPreListedMovies(@Path("i") String path, @Query("api_key") String apikey);


    @GET("3/genre/movie/list")
    Call<GenresDAO> getGenres(@Query("api_key") String apikey);

    @GET("3/discover/movie")
    Call<MoviesDAO> getMovies(@Query("api_key") String apikey,
                              @Query("with_genres") String genres,
                              @Query("primary_release_date.gte") String pr_rlsdate_gte,
                              @Query("primary_release_date.lte") String pr_rlsdate_lte,
                              @Query("vote_average.gte") String vote_avg,
                              @Query("sort_by") String sort,
                              @Query("page") String page
    );



    @GET("3/movie/{i}")
    Call<MovieDetailsDAO> getMovieFullDetails(@Path("i") String movieId,
                                              @Query("api_key") String apikey,
                                              @Query("append_to_response") String identifiers);


    @GET("3/movie/{i}")
    Call<MovieDetailsDAO> getMovieDetails(@Path("i") String movieId,
                                          @Query("api_key") String apikey);

    @GET("3/movie/{i}/images")
    Call<MovieImagesDAO> getMovieImages(@Path("i") String movieId,
                                        @Query("api_key") String apikey);

    @GET("3/movie/{i}/keywords")
    Call<MovieKeywordsDAO> getMovieKeywords(@Path("i") String movieId,
                                            @Query("api_key") String apikey);

    @GET("3/movie/{i}/similar")
    Call<MoviesDAO> getMovieSimilar(@Path("i") String movieId,
                                    @Query("api_key") String apikey);

    @GET("3/collection/{i}")
    Call<CollectionDAO> getCollection(@Path("i") String collectionId,
                                      @Query("api_key") String apikey,
                                      @Query("append_to_response") String identifier);



}
