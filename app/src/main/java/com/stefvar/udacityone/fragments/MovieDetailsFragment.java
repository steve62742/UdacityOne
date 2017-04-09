package com.stefvar.udacityone.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.meetic.marypopup.MaryPopup;
import com.squareup.picasso.Picasso;
import com.stefvar.udacityone.DAO.Movies.MovieDetailsDAO;
import com.stefvar.udacityone.DetailAdapter;
import com.stefvar.udacityone.MovieDetailsAdapter;
import com.stefvar.udacityone.R;
import com.stefvar.udacityone.ReviewAdapter;
import com.stefvar.udacityone.WordAdapter;
import com.stefvar.udacityone.customClasses.HeaderFrameLayout;
import com.stefvar.udacityone.customClasses.OutScrollView;
import com.stefvar.udacityone.customClasses.PullRelativeLayout;

import ayar.oktay.advancedtextview.AdvancedTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class MovieDetailsFragment extends Fragment
        implements PullRelativeLayout.OnStateChangeListener,
        OutScrollView.OnScrollStateChangeListener,
        View.OnClickListener,
        MovieDetailsAdapter.MovieDetailsAdapterInterface,
        DetailAdapter.MovieDetailsAdapterInterface {


    @BindView(R.id.tagline)
    TextView tagline;
    @BindView(R.id.tagline_card)
    CardView taglineCard;
    @BindView(R.id.director)
    TextView director;
    @BindView(R.id.releasedate)
    TextView releasedate;
    @BindView(R.id.runtime)
    TextView runtime;
    @BindView(R.id.budget)
    IconTextView budget;
    @BindView(R.id.revenue)
    IconTextView revenue;
    @BindView(R.id.votes)
    TextView votes;
    @BindView(R.id.details_card)
    CardView detailsCard;
    @BindView(R.id.castlistcard)
    CardView castlistcard;
    @BindView(R.id.similarlistcard)
    CardView similarlistcard;
    @BindView(R.id.genresgrid)
    RecyclerView genresgrid;
    @BindView(R.id.genres_card)
    CardView genresCard;
    @BindView(R.id.summary)
    AdvancedTextView summary;
    @BindView(R.id.summary_card)
    CardView summaryCard;
    @BindView(R.id.tagsgrid)
    RecyclerView tagsgrid;
    @BindView(R.id.tags_card)
    CardView tagsCard;
    @BindView(R.id.collectionimage)
    ImageView collectionimage;
    @BindView(R.id.collection_card)
    CardView collectionCard;
    @BindView(R.id.cast_list)
    RecyclerView castList;
    @BindView(R.id.similar_list)
    RecyclerView similarList;
    @BindView(R.id.first_star)
    ImageView firstStar;
    @BindView(R.id.second_star)
    ImageView secondStar;
    @BindView(R.id.third_star)
    ImageView thirdStar;
    @BindView(R.id.fourth_star)
    ImageView fourthStar;
    @BindView(R.id.fifth_star)
    ImageView fifthStar;
    @BindView(R.id.movie_rating)
    TextView movieRating;
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.main_header_icon)
    ImageView mainHeaderIcon;

    @BindView(R.id.main_rcv)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_open_iv)
    ImageButton mOpenIv;
    @BindView(R.id.main_header_mask)
    ImageView mMaskIv;
    @BindView(R.id.main_header)
    HeaderFrameLayout mHeaderFrameLayout;
    @BindView(R.id.main_info)
    LinearLayout mLinearLayoutInfo;
    @BindView(R.id.main_body)
    PullRelativeLayout mPullRelativeLayout;
    @BindView(R.id.main_root)
    OutScrollView mOutScrollView;
    @BindView(R.id.main_content)
    LinearLayout mContentView;
    @BindView(R.id.main_poster)
    ImageView mIconIv;


    @BindView(R.id.directoricon)
    IconTextView directoricon;
    @BindView(R.id.releasedateicon)
    IconTextView releasedateicon;
    @BindView(R.id.runtimeicon)
    IconTextView runtimeicon;
    @BindView(R.id.budgeticon)
    IconTextView budgeticon;
    @BindView(R.id.revenueicon)
    IconTextView revenueicon;
    @BindView(R.id.votesicon)
    IconTextView votesicon;
    @BindView(R.id.genresseparator)
    View genresseparator;
    @BindView(R.id.detailslayout)
    LinearLayout detailslayout;
    @BindView(R.id.summaryseparator)
    View summaryseparator;
    @BindView(R.id.tagsseparator)
    View tagsseparator;
    @BindView(R.id.collectionseparator)
    View collectionseparator;
    @BindView(R.id.castseparator)
    View castseparator;
    @BindView(R.id.similarseparator)
    View similarseparator;
    @BindView(R.id.castlayout)
    LinearLayout castlayout;


    @BindView(R.id.youtube)
    IconTextView youtube;
    @BindView(R.id.dbsave)
    IconTextView dbsave;
    @BindView(R.id.testrel)
    RelativeLayout testrel;
    @BindView(R.id.reviewseparator)
    View reviewseparator;
    @BindView(R.id.reviewgrid)
    RecyclerView reviewgrid;
    @BindView(R.id.review_card)
    CardView reviewCard;

    private MovieDetailsFragmentInterface fragmentInterface;


    public MaryPopup marypopup;

    private int mHeaderHeight;

    @Override
    public void getClickedImage(int position, View v, int type) {
        View popupImage = LayoutInflater.from(this.getContext()).inflate(R.layout.popup_image, null, false);
        ImageView test = (ImageView) popupImage.findViewById(R.id.bigimage);

        if (type != 0) {
            test.setAdjustViewBounds(false);
        }
        String url = "";
        if (type == 0) {
            url = "https://image.tmdb.org/t/p/w780" + movie.getImages().getBackdrops().get(position).getFilePath();
        } else if (type == 1) {
            url = "https://image.tmdb.org/t/p/h632" + movie.getCredits().getCast().get(position).getProfilePath();
        } else {
            url = "https://image.tmdb.org/t/p/w780" + movie.getSimilar().getResults().get(position).getPosterPath();
        }
        Picasso.with(this.getContext())
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerInside()
                .into(test);

        marypopup
                .content(popupImage)
                .from(v)
                .center(true)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //null.unbind();
    }


    /*

    -----------------

     */
    public interface MovieDetailsFragmentInterface {
        //void sendToMain(List<Integer> i);

        void getClickedMovie(Integer position);

        void getOpenDetails(Boolean open);

        void getCollectionClick();

        void openYoutube(String id);

        void saveMovieDB();

        void removeMovieDB(Integer id);

        Boolean checkSaved( Integer id);

    }

    /*
    Attach Detach gia interface
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            fragmentInterface = (MovieDetailsFragmentInterface) getActivity();
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString()
                    + " must implement SwipeCardsFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInterface = null;
    }


    /*

    -----------------

     */


    public MovieDetailsDAO movie;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    public void setData(MovieDetailsDAO currentMovie) {
        movie = currentMovie;
    }

    public MovieDetailsDAO getData() {
        return movie;
    }

    public void onBack() {

        openOutUi();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);


        if (savedInstanceState != null) {
            // Restore last state for checked position.
            //mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            //String json = savedInstanceState.getString("GRIDMOVIES");
            String stringDetails = savedInstanceState.getString("MOVIEDETAILS");
            Gson gson = new Gson();
            this.movie = gson.fromJson(stringDetails, MovieDetailsDAO.class);


        }

        marypopup = MaryPopup.with((Activity) this.getContext())
                .cancellable(true)
                .draggable(false)
                .center(true)
                .inlineMove(false)
                .scaleDownDragging(true)
                .shadow(false)
                .scaleDownCloseOnDrag(true)
                .openDuration(300)
                .closeDuration(500)
                .blackOverlayColor(Color.parseColor("#33000000"))
                .backgroundColor(Color.parseColor("#000000"));

        initData();
        initListener();

        colorSaved();


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void initData() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        MovieDetailsAdapter moviedetailsAdapter = new MovieDetailsAdapter();
        moviedetailsAdapter.getMovie(movie);
        moviedetailsAdapter.setItemCount(movie.getImages().getBackdrops().size());
        moviedetailsAdapter.setContext(getContext());
        moviedetailsAdapter.setContext(this.getContext());
        moviedetailsAdapter.setInterface(this);
        mRecyclerView.setAdapter(moviedetailsAdapter);

        String backdropURL = "https://image.tmdb.org/t/p/w780/";
        String posterURL = "https://image.tmdb.org/t/p/w185/";
        if (movie.getImages().getBackdrops().size() > 0) {
            backdropURL = backdropURL + movie.getImages().getBackdrops().get(0).getFilePath();
        }
        if (movie.getImages().getPosters().size() > 0) {
            posterURL = posterURL + movie.getImages().getPosters().get(0).getFilePath();
        }
        Picasso.with(this.getContext())
                .load(backdropURL)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(mainHeaderIcon);

        Picasso.with(this.getContext())
                .load(posterURL)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(mIconIv);

        if (movie.getVoteAverage() != null) {
            movieRating.setText(movie.getVoteAverage().toString());
        } else {
            movieRating.setText("-");
        }
        movieTitle.setText(movie.getTitle());

        if (movie.getVoteAverage() <= 8) {
            fifthStar.setVisibility(View.INVISIBLE);
        }
        if (movie.getVoteAverage() <= 6) {
            fourthStar.setVisibility(View.INVISIBLE);
        }
        if (movie.getVoteAverage() <= 4) {
            thirdStar.setVisibility(View.INVISIBLE);
        }
        if (movie.getVoteAverage() <= 2) {
            secondStar.setVisibility(View.INVISIBLE);
        }

        if (movie.getCredits().getJob("Director") != null) {
            director.setText(movie.getCredits().getJob("Director").getName());
        } else {
            director.setText("-");
        }
        if (movie.getReleaseDate() != null) {
            releasedate.setText(movie.getReleaseDate());
        } else {
            releasedate.setText("-");
        }
        if (movie.getRuntime() != null) {
            runtime.setText(movie.getRuntime().toString() + "'");
        } else {
            runtime.setText("-");
        }
        if (movie.getBudget() == 0) {
            budget.setText("-");
        } else {
            budget.setText(String.valueOf(movie.getBudget() / 1000000) + "M {ion-social-usd}");
        }
        if (movie.getRevenue() == 0) {
            revenue.setText("-");
        } else {
            revenue.setText(String.valueOf(movie.getRevenue() / 1000000) + "M {ion-social-usd}");
        }
        votes.setText(movie.getVoteCount().toString());
        if (movie.getOverview() != null && movie.getOverview().length() > 0) {
            summaryCard.setVisibility(View.VISIBLE);
            summary.setText(movie.getOverview());
        } else {
            summaryCard.setVisibility(View.GONE);
        }
        if (movie.getTagline() != null && movie.getTagline().length() > 0) {
            taglineCard.setVisibility(View.VISIBLE);
            tagline.setText(movie.getTagline());
        } else {
            taglineCard.setVisibility(View.GONE);
        }
        if (movie.getCredits().getCast().size() > 0) {
            castlistcard.setVisibility(View.VISIBLE);
            DetailAdapter castAdapter = new DetailAdapter(this.getContext(), this, 0);
            castAdapter.setCast(movie.getCredits().getCast());
            castList.setAdapter(castAdapter);
        } else {
            castlistcard.setVisibility(View.GONE);
        }
        if (movie.getSimilar().getResults().size() > 0) {
            similarlistcard.setVisibility(View.VISIBLE);
            DetailAdapter similarAdapter = new DetailAdapter(this.getContext(), this, 1);
            similarAdapter.setSimilar(movie.getSimilar().getResults());
            similarList.setAdapter(similarAdapter);
        } else {
            similarlistcard.setVisibility(View.GONE);
        }
        if (movie.getReviews().getResults().size() > 0) {
            reviewCard.setVisibility(View.VISIBLE);
            ReviewAdapter reviewAdapter = new ReviewAdapter(this.getContext());
            reviewAdapter.setReviews(movie.getReviews().getResults());
            reviewgrid.setAdapter(reviewAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            reviewgrid.setLayoutManager(layoutManager);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        } else {
            reviewCard.setVisibility(View.GONE);
        }
        if (movie.getKeywords().getKeywords().size() > 0) {
            tagsCard.setVisibility(View.VISIBLE);
            WordAdapter tagsAdapter = new WordAdapter(this.getContext(), movie.getKeywords());
            tagsgrid.setAdapter(tagsAdapter);
            tagsgrid.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
            tagsgrid.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.set(5, 5, 5, 5);
                }
            });
        } else {
            tagsCard.setVisibility(View.GONE);
        }
        if (movie.getGenres().size() > 0) {
            genresCard.setVisibility(View.VISIBLE);
            WordAdapter genresAdapter = new WordAdapter(this.getContext(), movie.getGenres());
            genresgrid.setAdapter(genresAdapter);
            genresgrid.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            genresgrid.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.set(5, 5, 5, 5);
                }
            });

        } else {
            genresCard.setVisibility(View.GONE);
        }
        if (movie.getBelongsToCollection() == null) {
            collectionCard.setVisibility(View.GONE);
        } else {
            collectionCard.setVisibility(View.VISIBLE);
            if (movie.getBelongsToCollection().getBackdropPath() != null) {
                Picasso.with(this.getContext())
                        .load("https://image.tmdb.org/t/p/w780/" + movie.getBelongsToCollection().getBackdropPath())
                        .error(R.drawable.error)
                        .placeholder(R.drawable.placeholder)
                        .fit()
                        .centerInside()
                        .into(collectionimage);
            } else {
                Picasso.with(this.getContext())
                        .load(backdropURL)
                        .error(R.drawable.error)
                        .placeholder(R.drawable.placeholder)
                        .fit()
                        .centerInside()
                        .into(collectionimage);
            }
        }


    }

    private void colorSaved(){
        if ( fragmentInterface.checkSaved(movie.getId())){
            dbsave.setTextColor( getResources().getColor( R.color.colorAccent  ) );
        }else{
            dbsave.setTextColor( getResources().getColor( R.color.colorPrimaryLight  ) );
        }
    }

    private void initListener() {
        mPullRelativeLayout.setOnStateChangeListener(this);
        mOutScrollView.setOnScrollStateChangeListener(this);
        mOpenIv.setOnClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mPullRelativeLayout.getState() == PullRelativeLayout.HIDE) {
                    mOutScrollView.scrollBy(dx, dy);
                }
            }
        });
        mPullRelativeLayout.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mHeaderHeight = mHeaderFrameLayout.getMeasuredHeight();
                        int iconHeight = mIconIv.getMeasuredHeight();
                        int pullRelMarTop = mHeaderHeight - (iconHeight / 2);
                        setPullRelativeLayoutMarTop(pullRelMarTop);
                        setContentViewMarTop(iconHeight / 2);
                        mPullRelativeLayout.setMaxOffset(iconHeight / 2);
                        testAnim();
                        mPullRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

        //youtube.setOnClickListener(this);
    }

    private void testAnim() {
        mHeaderFrameLayout.setOpen(true);
        mPullRelativeLayout.hide(100);
        mPullRelativeLayout.open();
        mHeaderFrameLayout.open();
        AnimationDrawable animationDrawable = (AnimationDrawable) mOpenIv.getDrawable();
        animationDrawable.start();
    }

    private void setPullRelativeLayoutMarTop(int top) {
        RelativeLayout.LayoutParams mPullLayoutParams =
                (RelativeLayout.LayoutParams) mPullRelativeLayout.getLayoutParams();
        mPullLayoutParams.setMargins(0, top, 0, 0);
    }

    private void setContentViewMarTop(int top) {
        RelativeLayout.LayoutParams mContentViewParams =
                (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
        mContentViewParams.setMargins(0, top, 0, 0);
    }

    @Override
    public void pullViewShow(int state) {
        mOutScrollView.setPullRelativeLayoutState(state);
        mLinearLayoutInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void pullViewHide(int state) {
        mOutScrollView.setPullRelativeLayoutState(state);
        mHeaderFrameLayout.setVisibility(View.INVISIBLE);
        fragmentInterface.getOpenDetails(true);
    }

    @Override
    public void pullViewMove(int state, int offset) {
        mOutScrollView.setPullRelativeLayoutState(state);
        mLinearLayoutInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void pullViewOpenStart() {
        if (mHeaderFrameLayout.isOpen()) {
            mOutScrollView.scrollTo(0, 0);
        }
        mHeaderFrameLayout.setVisibility(View.VISIBLE);
        mLinearLayoutInfo.setVisibility(View.VISIBLE);
        mMaskIv.setVisibility(View.INVISIBLE);
        fragmentInterface.getOpenDetails(false);
    }

    @Override
    public void pullViewOpenFinish() {
        mMaskIv.setVisibility(View.VISIBLE);
        LinearLayoutManager manager =
                (LinearLayoutManager) mRecyclerView.getLayoutManager();
        manager.scrollToPosition(0);
    }

    @Override
    public void onScrollChange(int l, int t, int oldl, int oldt) {
        int offset = (int) (mHeaderHeight * 0.7);
        if (t > offset && mPullRelativeLayout.getState() == PullRelativeLayout.HIDE) {
            mHeaderFrameLayout.setOpen(true);
        }
    }


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



    @OnClick({R.id.youtube, R.id.dbsave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.youtube:
                openYoutube("QyO1dRCNnTY");
                break;
            case R.id.dbsave:

                if ( fragmentInterface.checkSaved(movie.getId())){
                    fragmentInterface.removeMovieDB(movie.getId());
                }else{
                    fragmentInterface.saveMovieDB();
                }
                colorSaved();

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_open_iv:
                openOutUi();
                break;
        }
    }

    private void openOutUi() {
        mPullRelativeLayout.open();
        mHeaderFrameLayout.open();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        Gson gson = new Gson();
        String stringDetails = "";
        stringDetails = gson.toJson(this.movie);

        savedInstanceState.putString("MOVIEDETAILS", stringDetails);

        // etc.
    }


}
