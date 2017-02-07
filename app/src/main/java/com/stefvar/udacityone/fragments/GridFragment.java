package com.stefvar.udacityone.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.stefvar.udacityone.DAO.Movies.FocusMovieDAO;
import com.stefvar.udacityone.GridAdapter;
import com.stefvar.udacityone.R;

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

        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        createCustomAdapter(recyclerView, linearLayoutManager);

        return view;
    }


    private void createCustomAdapter(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        GridAdapter focusmovieAdapter = new GridAdapter(this.getContext(), (int) getResources().getDimension(R.dimen.custom_item_height) , this);
        focusmovieAdapter.addItems(this.movies);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(focusmovieAdapter);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }




    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }



    public void setData(List<FocusMovieDAO> movies) {
        this.movies = movies;
    }


}
