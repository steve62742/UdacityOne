package com.stefvar.udacityone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stefvar.udacityone.DAO.MovieDetails.MovieCastDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieSimilarResultsDAO;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class DetailAdapter extends
        RecyclerView.Adapter {


    private List<MovieCastDAO> cast;
    private List<MovieSimilarResultsDAO> similar;
    private Context context;
    private MovieDetailsAdapterInterface movieDetailsAdapterInterface;
    private Integer type;

    public interface MovieDetailsAdapterInterface {
        void getClickedImage(int position, View v, int type);
    }



    public DetailAdapter(Context ctx , MovieDetailsAdapterInterface intface , Integer tp  ) {
        this.context = ctx;
        movieDetailsAdapterInterface = intface;
        type = tp;
    }

    public void setSimilar(List<MovieSimilarResultsDAO> similarList){
        similar = similarList;
    }
    public void setCast (List<MovieCastDAO> castList){
        cast = castList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View branchView;
        if (type == 0){
            branchView = inflater.inflate(R.layout.creditface, parent, false);
            return new CastHolder(branchView);
        }else{
            branchView = inflater.inflate(R.layout.similarcover, parent, false);
            return new SimilarHolder(branchView);
        }
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (type==0){
            final MovieCastDAO person = cast.get(position);

            CastHolder viewHolder = (CastHolder) holder;
            Picasso.with(context)
                    .load( "http://image.tmdb.org/t/p/w185" + person.getProfilePath() )
                    .placeholder(R.drawable.person_placeholder)
                    .error(R.drawable.person_placeholder)
                    .noFade()
                    .into(viewHolder.avatar);


            viewHolder.avatar.setBorderColor( context.getResources().getColor(R.color.colorAccent) );

            viewHolder.name.setText(person.getName());
            viewHolder.character.setText(person.getCharacter());

            if (person.getProfilePath()!= null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDetailsAdapterInterface.getClickedImage(position , holder.itemView , 1);
                    }
                });
            }else {
                viewHolder.itemView.setClickable(false);
            }


        }else if(type == 1){
            final MovieSimilarResultsDAO similarMovie = similar.get(position);

            SimilarHolder viewHolder = (SimilarHolder) holder;
            Picasso.with(context)
                    .load( "http://image.tmdb.org/t/p/w185" + similarMovie.getPosterPath() )
                    .placeholder(R.drawable.person_placeholder)
                    .error(R.drawable.error)
                    .noFade()
                    .into(viewHolder.cover);

            viewHolder.name.setText(similarMovie.getOriginalTitle() );

            if (similarMovie.getPosterPath()!=null){
                viewHolder.itemView.setClickable(false);
            }else{
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDetailsAdapterInterface.getClickedImage(position , holder.itemView , 2);
                    }
                });
            }


        }


    }


    @Override
    public int getItemCount() {
        if (type==0){
            return cast.size();
        }else {
            return similar.size();
        }
    }



    public class CastHolder extends RecyclerView.ViewHolder {
        public CircleImageView avatar;
        public TextView name;
        public TextView character;
        public Integer position;

        public CastHolder(View itemView) {
            super(itemView);

            avatar = (CircleImageView) itemView.findViewById(R.id.castprofile_image);
            name = (TextView) itemView.findViewById(R.id.castprofile_name);
            character = (TextView) itemView.findViewById(R.id.castprofile_character);


        }
    }

    public class SimilarHolder extends RecyclerView.ViewHolder {
        public ImageView cover;
        public TextView name;
        public Integer position;

        public SimilarHolder(View itemView) {
            super(itemView);

            cover = (ImageView) itemView.findViewById(R.id.similar_image);
            name = (TextView) itemView.findViewById(R.id.similar_name);


        }
    }
}
