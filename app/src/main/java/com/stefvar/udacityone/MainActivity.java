package com.stefvar.udacityone;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.stefvar.udacityone.DAO.Movies.FocusMovieDAO;
import com.stefvar.udacityone.DAO.Movies.MovieDAO;
import com.stefvar.udacityone.DAO.Movies.MovieDetailsDAO;
import com.stefvar.udacityone.DAO.Movies.MoviesDAO;
import com.stefvar.udacityone.api.ApiClient;
import com.stefvar.udacityone.api.ApiInterface;
import com.stefvar.udacityone.fragments.GridFragment;
import com.stefvar.udacityone.fragments.LoadingFragment;
import com.stefvar.udacityone.fragments.MovieDetailsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        //implements
        GridFragment.GridFragmentInterface,
        MovieDetailsFragment.MovieDetailsFragmentInterface,
        LoadingFragment.LoadingFragmentInterface{







    LoadingFragment loadingFragment;
    GridFragment gridFragment;
    MovieDetailsFragment movieDetailes;

    private MoviesDAO movies = new MoviesDAO();
    private HashMap<String, MoviesDAO> hash_movies = new HashMap<>();
    private MovieDetailsDAO movie = new MovieDetailsDAO();


    private String API_KEY =  "YOUR_API_KEY";

    boolean isOpenMovieDetails = false;
    int count_api = 10;
    Integer clickedMovie;
    String movieType = "popular";



    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_activity_content)
    FrameLayout mainActivityContent;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        //gridFragment = new GridFragment();

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            //mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            //String json = savedInstanceState.getString("GRIDMOVIES");
            Gson gson = new Gson();
            if (savedInstanceState.containsKey("POPULAR") ){
                hash_movies.put("popular" , gson.fromJson(  savedInstanceState.getString("POPULAR") , MoviesDAO.class ) );
            }
            if (savedInstanceState.containsKey("TOPRATED") ){
                hash_movies.put("top_rated" , gson.fromJson(  savedInstanceState.getString("TOPRATED") , MoviesDAO.class ) );
            }
            movieType = savedInstanceState.getString("TYPE");
            if (movieType.equals("SAVED")){

            }else{
                movies = hash_movies.get(movieType);
            }
            if (savedInstanceState.containsKey("CLICKED") ){
                clickedMovie = savedInstanceState.getInt("CLICKED");
            }
        }else {
            getMovies();
        }



    }


    public void changeFragment(Fragment nextFragment, String tag) {
        LoadingFragment loading = (LoadingFragment) getSupportFragmentManager().findFragmentByTag("LOADING");
        if (loading != null) {
            getSupportFragmentManager().beginTransaction().remove(loading).commit();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_content, nextFragment, tag)
                .addToBackStack(tag)
                .commit();

    }


    public void showLoading() {
        //if (reshowFragment("LOADING") == false){
        if (loadingFragment == null){
            loadingFragment = new LoadingFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_activity_content, loadingFragment, "LOADING")
                .commit();
        //}
        //loadingFragment.setColors(customColors.getThemeColors());
    }



    @Override
    public void getOpenDetails(Boolean open) {
        isOpenMovieDetails = open;
    }

    @Override
    public void getCollectionClick() {
    }

    @Override
    public void openYoutube(String id){
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            // youtube is not installed.Will be opened in other available apps

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v=" + id));
            startActivity(i);
        }
    }


    @Override
    public void getClickedMovie(Integer position) {
        clickedMovie = position;
        getMovieDetails(position);
    }

    @Override
    public void saveMovieDB(){
        //MovieDAO currentMovie = movies.getResults().get(clickedMovie);
        if ( !checkSaved( movie.getId() ) )   {
            List<FocusMovieDAO> savedlist = new ArrayList<>();
            List<Integer> savedIds = new ArrayList<>();
            if ( Hawk.contains("movies") ){
                savedlist = Hawk.get("movies");
            }
            if ( Hawk.contains("movieids")){
                savedIds = Hawk.get("movieids");
            }
            FocusMovieDAO saving = new FocusMovieDAO(movie.getTitle(), "" + movie.getVoteAverage(), movie.getPosterPath() );
            MovieDetailsDAO tst = movie;
            saving.setMovieDetails(tst);
            savedlist.add( saving );
            Hawk.put("movies" , savedlist );
            savedIds.add(tst.getId());
            Hawk.put("movieids", savedIds);
        }
    }

    @Override
    public void removeMovieDB(Integer movieID){

        //MovieDAO currentMovie = movies.getResults().get(clickedMovie);


        if ( checkSaved( movieID ) )   {

            List<Integer> savedIds = new ArrayList<>();
            List<FocusMovieDAO> savedlist = new ArrayList<>();
            if ( Hawk.contains("movies") ){
                savedlist = Hawk.get("movies");
            }
            if ( Hawk.contains("movieids")){
                savedIds = Hawk.get("movieids");
            }
            savedlist.remove( savedIds.indexOf( movieID ) );
            savedIds.remove( savedIds.indexOf( movieID ) );

            Hawk.put("movies" , savedlist );
            Hawk.put("movieids" , savedIds );
        }
    }

    @Override
    public Boolean checkSaved(Integer id){
        List<Integer> savedIds = new ArrayList<>();
        if ( Hawk.contains("movieids")){
            savedIds = Hawk.get("movieids");
            if ( savedIds.contains(id) ){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }

    public void getMovieDetails(final Integer moviePosition) {

        showLoading();

        if (movieType.equals("saved")) {

            List<FocusMovieDAO> savedMovies = Hawk.get("movies");
            movie = savedMovies.get(moviePosition).getMovieDetails();
            movieDetailes = new MovieDetailsFragment();
            movieDetailes.setData(movie);
            changeFragment(movieDetailes, "DETAILS");

        }else{

            final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            final String movieId = String.valueOf(movies.getResults().get(moviePosition).getId());
            String identifiers = "images,keywords,similar,lists,credits,videos,reviews";
            Call<MovieDetailsDAO> call = apiService.getMovieFullDetails(movieId, API_KEY, identifiers);
            call.enqueue(new Callback<MovieDetailsDAO>() {
                @Override
                public void onResponse(Call<MovieDetailsDAO> call, Response<MovieDetailsDAO> response) {
                    if (response.code() == 200) {
                        movie = response.body();
                        movieDetailes = new MovieDetailsFragment();
                        movieDetailes.setData(movie);
                        changeFragment(movieDetailes, "DETAILS");
                    } else {
                        Log.e("test2", "kodikos: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<MovieDetailsDAO> call, Throwable t) {
                    //showError("There seems to be a problem, are you connected to the Internet?");
                    System.out.println("Failed call " + t);
                    System.out.println("stack trace: ");
                    Log.e("test2", t.toString());
                }
            });


        }



    }



    public void getMovies() {

        if (movieType.equals("saved")){

            showLoading();

            //movies = response.body();
            //hash_movies.put(movieType, movies);
            gridFragment = new GridFragment();
            List<FocusMovieDAO> savedMovies = Hawk.get("movies");


//            List<FocusMovieDAO> testlist = new ArrayList<>();
//            List<MovieDAO> fetchedMovies = movies.getResults();
//            for (int i = 0; i < count_api; i++) {
//                MovieDAO currentMovie = fetchedMovies.get(i);
//
//                testlist.add(new FocusMovieDAO(currentMovie.getTitle(), "" + currentMovie.getVoteAverage(), currentMovie.getPosterPath() ));
//            }
            gridFragment.setData(savedMovies);
            changeFragment(gridFragment, movieType);



        }else{


            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<MoviesDAO> call = apiService.getPreListedMovies(movieType, API_KEY);
            showLoading();
            call.enqueue(new Callback<MoviesDAO>() {
                @Override
                public void onResponse(Call<MoviesDAO> call, Response<MoviesDAO> response) {
                    if (response.code() == 200) {
                        movies = response.body();
                        hash_movies.put(movieType, movies);

                        gridFragment = new GridFragment();
                        List<FocusMovieDAO> testlist = new ArrayList<>();
                        List<MovieDAO> fetchedMovies = movies.getResults();
                        for (int i = 0; i < count_api; i++) {
                            MovieDAO currentMovie = fetchedMovies.get(i);

                            testlist.add(new FocusMovieDAO(currentMovie.getTitle(), "" + currentMovie.getVoteAverage(), currentMovie.getPosterPath() ));
                        }
                        gridFragment.setData(testlist);
                        changeFragment(gridFragment, movieType);

                    } else {
                        Log.e("test2", "kodikos: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<MoviesDAO> call, Throwable t) {
                    //showError("There seems to be a problem, are you connected to the Internet?");
                    System.out.println("Failed call " + t);
                    System.out.println("stack trace: ");
                    Log.e("test2", t.toString());
                }
            });
            //}



        }

    }





















    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        FragmentManager fragmentM = getSupportFragmentManager();

        MovieDetailsFragment details = (MovieDetailsFragment) fragmentM.findFragmentByTag("DETAILS");

        if (details != null && details.isVisible() && details.marypopup.isOpened()) {
            details.marypopup.close(true);
        } else if (details != null && details.isVisible() && isOpenMovieDetails == true) {
            movieDetailes.onBack();
        } else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            int count = fragmentM.getBackStackEntryCount();
            for (int j = 0; j < count; j++) {
                Log.d("thesi " + j + " : ", fragmentM.getBackStackEntryAt(j).getName());
            }
            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else if (count == 1) {
                fragmentM.popBackStack();
                super.onBackPressed();
            } else {
                FragmentManager.BackStackEntry test = fragmentM.getBackStackEntryAt(count - 2);
                if (!String.valueOf(test.getName()).equals("DETAILS") && !String.valueOf(test.getName()).equals("QUESTIONS") && !String.valueOf(test.getName()).equals("ABOUT") ) {
                    movies = hash_movies.get(String.valueOf(test.getName()));
                    GridFragment saved = (GridFragment) fragmentM.findFragmentByTag(test.getName());
                    switch ( String.valueOf(test.getName())  ) {
                        case "popular":
                            navView.setCheckedItem(R.id.nav_popular);
                            movieType = "popular";
                            break;
                        case "top_rated":
                            navView.setCheckedItem(R.id.nav_rated);
                            movieType = "movieType";
                            break;
                        case "saved":

                            List<FocusMovieDAO> savedMovies = Hawk.get("movies");
                            saved.setData(savedMovies);
                            saved.test();

                            navView.setCheckedItem(R.id.nav_saved);
                            movieType = "saved";
                            break;
                    }
                }
                if (String.valueOf(test.getName()).equals("ABOUT")) {
                    for (int menp = 0; menp < navView.getMenu().size() ; menp++){
                        navView.getMenu().getItem(menp).setChecked(false);
                    }
                }
                if (String.valueOf(test.getName()).equals("DETAILS")) {
                    for (int menp = 0; menp < navView.getMenu().size() ; menp++){
                        navView.getMenu().getItem(menp).setChecked(false);
                    }
                }

                fragmentM.popBackStack();
                LoadingFragment loading = (LoadingFragment) fragmentM.findFragmentByTag("LOADING");
                if (loading != null) {
                    fragmentM.beginTransaction().remove(loading).commit();
                }
            }
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_popular && !item.isChecked() ) {

            //getMovies("popular");
            movieType = "popular";
            getMovies();

        } else if (id == R.id.nav_rated && !item.isChecked() ) {
            //getMovies("top_rated");
            movieType = "top_rated";
            getMovies();

        } else if (id == R.id.nav_saved && !item.isChecked() ) {
            //getMovies("top_rated");
            movieType = "saved";
            getMovies();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        Gson gson = new Gson();
        //String json = gson.toJson(this.movies);

        String json = "";
        savedInstanceState.putString("TYPE" , movieType);

        if (hash_movies.containsKey("popular") ){
            String popularMovies = gson.toJson( hash_movies.get("popular") ) ;
            savedInstanceState.putString("POPULAR" , popularMovies );
        }
        if (hash_movies.containsKey("top_rated") ){
            String topRatedMovies = gson.toJson( hash_movies.get("top_rated") ) ;
            savedInstanceState.putString("TOPRATED" , topRatedMovies );
        }

        if (clickedMovie != null){
            savedInstanceState.putInt("CLICKED" , clickedMovie);
        }

        // etc.
    }





}
