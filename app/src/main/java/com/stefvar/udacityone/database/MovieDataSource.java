package com.stefvar.udacityone.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.stefvar.udacityone.DAO.Movies.FocusMovieDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 11/4/2017.
 */

public class MovieDataSource {
    // Database fields
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    Gson gson = new Gson();
    private String[] allColumns = { DataBaseHelper.COLUMN_ID,
            DataBaseHelper.COLUMN_MOVIE };

    public MovieDataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Boolean saveMovie(FocusMovieDAO movie) {
        ContentValues values = new ContentValues();

        values.put( DataBaseHelper.COLUMN_MOVIE, gson.toJson(movie)  );
        values.put( DataBaseHelper.COLUMN_ID , movie.getMovieDetails().getId() );
        try{
            database.insertOrThrow(DataBaseHelper.TABLE_MOVIES, null,
                    values);
            return true;
        }catch (SQLException e){
            return false;
        }

    }

    public void deleteMovie(MovieJSON movie) {
        long id = movie.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseHelper.TABLE_MOVIES, DataBaseHelper.COLUMN_ID
                + " = " + id, null);
    }
    public void deleteMovie(long ID) {
        System.out.println("Comment deleted with id: " + ID);
        database.delete(DataBaseHelper.TABLE_MOVIES, DataBaseHelper.COLUMN_ID
                + " = " + (long) ID, null);
    }

    public List<MovieJSON> getAllMovies() {
        List<MovieJSON> movies = new ArrayList<MovieJSON>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_MOVIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MovieJSON movie = cursorToMovie(cursor);
            movies.add(movie);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return movies;
    }

    public FocusMovieDAO getMovie(long ID){
        Cursor cursor = database.query(DataBaseHelper.TABLE_MOVIES,
                allColumns, DataBaseHelper.COLUMN_ID + " = " + ID, null,
                null, null, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            MovieJSON foundMovie = cursorToMovie(cursor);
            return foundMovie.getMovie();
        }else{
            return null;
        }

    }

    public List<FocusMovieDAO> getMovies(){
        List<FocusMovieDAO> movies = new ArrayList<FocusMovieDAO>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_MOVIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MovieJSON movie = cursorToMovie(cursor);

            System.out.println("====== id: " + movie.getId());
            movies.add(movie.getMovie());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return movies;
    }



    private MovieJSON cursorToMovie(Cursor cursor) {
        MovieJSON movie = new MovieJSON();
        movie.setId(cursor.getLong(0));
        movie.setmovieJSON(cursor.getString(1));
        return movie;
    }



}
