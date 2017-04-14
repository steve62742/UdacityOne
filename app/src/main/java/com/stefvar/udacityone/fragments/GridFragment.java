package com.stefvar.udacityone.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.stefvar.udacityone.DAO.Movies.FocusMovieDAO;
import com.stefvar.udacityone.GridAdapter;
import com.stefvar.udacityone.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve62742 on 5/2/2017.
 */

public class GridFragment extends Fragment
        implements GridAdapter.GridAdapterInterface{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.focusmovieslayout)
    FrameLayout focusmovieslayout;
    private GridFragmentInterface GridInterface;

    private List<FocusMovieDAO> movies;

    GridAdapter focusmovieAdapter;

    @Override
    public void sendViewPosition(int position) {
        GridInterface.getClickedMovie(position);
    }


    public interface GridFragmentInterface {

        void getClickedMovie(Integer position);
    }

    /*
    Attach Detach gia interface
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            GridInterface = (GridFragmentInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SwipeCardsFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        GridInterface = null;
    }

    /*

    -----------------

     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (savedInstanceState != null) {
            // Restore last state for checked position.
            //mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            //String json = savedInstanceState.getString("GRIDMOVIES");
            ArrayList stringMovies = savedInstanceState.getStringArrayList("GRIDMOVIES");
            Gson gson = new Gson();
            if (this.movies == null){
                //List<FocusMovieDAO> this.movies = new ArrayList<FocusMovieDAO>();
                this.movies = new ArrayList<FocusMovieDAO>();

            }
            if (this.movies.size()==0){
                for (int i = 0; i < stringMovies.size(); i++){
                    this.movies.add(gson.fromJson( stringMovies.get(i).toString(), FocusMovieDAO.class)  );
                }
            }

        }



        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext() , 2);
        createCustomAdapter(recyclerView, gridLayoutManager);


        return view;
    }



    private void createCustomAdapter(RecyclerView recyclerView, GridLayoutManager gridLayoutManager) {

        focusmovieAdapter = new GridAdapter(this.getContext(), (int) getResources().getDimension(R.dimen.custom_item_height) , this);
        if (this.movies != null){
            focusmovieAdapter.addItems(this.movies);
        }
        if (recyclerView != null) {
            recyclerView.setLayoutManager(gridLayoutManager);

            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(focusmovieAdapter);
        }
    }







    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }



    public void setData(List<FocusMovieDAO> movies) {
        this.movies = movies;
    }

    public void test(){
        //this.focusmovieAdapter.cle
        if (this.focusmovieAdapter!=null){
            this.focusmovieAdapter.notifyDataSetChanged();
        }
    }




    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        Gson gson = new Gson();
        ArrayList stringMovies = new ArrayList();
        if (this.movies.size()>0){
            for (int i = 0; i<this.movies.size(); i++){
                stringMovies.add( gson.toJson(this.movies.get(i)) );
            }
        }
        savedInstanceState.putStringArrayList("GRIDMOVIES" , stringMovies );

        // etc.
    }




}
