package com.stefvar.udacityone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stefvar.udacityone.DAO.MovieDetails.MovieReview;

import java.util.List;


public class ReviewAdapter extends
        RecyclerView.Adapter {


    private List<MovieReview> reviews;
    private Context context;
    //private MovieDetailsAdapterInterface movieDetailsAdapterInterface;
    private Integer type;

//    public interface MovieDetailsAdapterInterface {
//        void getClickedImage(int position, View v, int type);
//    }



    public ReviewAdapter(Context ctx    ) {
        this.context = ctx;
        //movieDetailsAdapterInterface = intface;
    }


    public void setReviews (List<MovieReview> movieReviews) { reviews = movieReviews; }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View branchView;
        branchView = inflater.inflate(R.layout.review, parent, false);
        return new ReviewHolder(branchView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final MovieReview review = reviews.get(position);
        ReviewHolder viewHolder = (ReviewHolder) holder;
        viewHolder.name.setText(review.getAuthor()  );
        viewHolder.review.setText(review.getContent().replaceAll( "(\r)|(\n)" , "")  );



    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }




    public class ReviewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView review;

        public ReviewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.reviewer_name);
            review = (TextView) itemView.findViewById(R.id.review);


        }
    }

}
