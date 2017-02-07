package com.stefvar.udacityone.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.labo.kaji.fragmentanimations.MoveAnimation;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;
import com.stefvar.udacityone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve62742 on 23/7/2016.
 */
public class LoadingFragment extends Fragment {


    @BindView(R.id.circularFillableLoaders)
    CircularFillableLoaders circularFillableLoaders;
    @BindView(R.id.loadingcard)
    CardView loadingcard;
    @BindView(R.id.loadinglayout)
    LinearLayout loadinglayout;

    private LoadingFragmentInterface loadingFragmentInterface;

    public interface LoadingFragmentInterface {
        //void sendToMain(List<Integer> i);

        void getClickedMovie(Integer position);

        void getOpenDetails(Boolean open);

        void getCollectionClick();

    }


    public LoadingFragment() {
        // Required empty public constructor
    }



    /*
    Attach Detach gia interface
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            loadingFragmentInterface = (LoadingFragmentInterface) getActivity();
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString()
                    + " must implement SwipeCardsFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loadingFragmentInterface = null;
    }


    /*

    -----------------

     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setColors();
    }


    public void changeProgress(Integer progress) {
        if (circularFillableLoaders != null) {
            circularFillableLoaders.setProgress(progress);
        }
    }
    public void setColors() {


        circularFillableLoaders.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){

            @Override
            public boolean onPreDraw() {
                try {
                    circularFillableLoaders.setColor( getResources().getColor( R.color.colorAccent  ) );
                    return true;    //note, that "true" is important, since you don't want drawing pass to be canceled
                } finally {
                    circularFillableLoaders.getViewTreeObserver().removeOnPreDrawListener(this);    //we don't need any further notifications
                }
            }
        });



    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return MoveAnimation.create(MoveAnimation.UP, enter, 500);
        } else {
            return CubeAnimation.create(CubeAnimation.UP, enter, 500);
        }
    }


}
