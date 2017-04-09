package com.stefvar.udacityone.DAO.Movies;

import java.io.Serializable;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class FocusMovieDAO  implements Serializable {




    String title;
    String subTitle;
    String drawable;

    MovieDetailsDAO movieDetails = null;


    public FocusMovieDAO(String title, String subTitle, String drawable)  {

        this.title = title;
        this.subTitle = subTitle;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public MovieDetailsDAO getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetailsDAO movieDetails) {
        this.movieDetails = movieDetails;
    }

    public String getJSON(){
        return "{'title':'"+this.title+"' , 'subtitle':"+this.subTitle+"' , 'drawable':'"+this.drawable+"'}";
    }
    public void setJSON(String jsonString){

    }



}
