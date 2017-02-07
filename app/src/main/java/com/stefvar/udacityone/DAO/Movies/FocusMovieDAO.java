package com.stefvar.udacityone.DAO.Movies;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class FocusMovieDAO {



    String title;
    String subTitle;
    String drawable;

    public FocusMovieDAO(String title, String subTitle, String drawable) {
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



}