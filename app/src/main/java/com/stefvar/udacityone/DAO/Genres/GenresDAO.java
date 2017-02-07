package com.stefvar.udacityone.DAO.Genres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 12/7/2016.
 */
public class GenresDAO {


        @SerializedName("genres")
        @Expose
        private List<GenreDAO> genres = new ArrayList<GenreDAO>();

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


        public Integer getGenreId(String genreName) {
                Integer genreId = null;
                for (int i =0; i< genres.size();i++){
                        if (genres.get(i).getName().equals(genreName)){
                                genreId = genres.get(i).getId();
                                break;
                        }
                }
                return genreId;

        }

}
