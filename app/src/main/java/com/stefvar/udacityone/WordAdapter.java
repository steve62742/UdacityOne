package com.stefvar.udacityone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stefvar.udacityone.DAO.Genres.GenreDAO;
import com.stefvar.udacityone.DAO.MovieDetails.MovieKeywordsDAO;

import java.util.List;

/**
 * Created by steve62742 on 19/7/2016.
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.TagHolder> {


    private Context context;
    private MovieKeywordsDAO keyDAO;
    private List<GenreDAO> genreDao;
    private Integer type;
    //private CustomColors colorTheme;

    public WordAdapter(Context context, MovieKeywordsDAO data ) {
        this.context = context;
        this.keyDAO = data;
        type = 0;

    }
    public WordAdapter(Context context, List<GenreDAO> data) {
        this.context = context;
        this.genreDao = data;
        type = 1;

    }


    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.word, parent , false);
        view.setBackgroundColor( parent.getResources().getColor( R.color.colorAccent) );
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {

        if (type ==0 ){
            holder.tag.setText(keyDAO.getKeywords().get(position).getName());
        }else{
            holder.tag.setText(genreDao.get(position).getName());
        }
        holder.tag.setClickable(false);

    }

    public class TagHolder extends RecyclerView.ViewHolder {
        public TextView tag;
        public Integer position;
        public TagHolder(View itemView) {
            super(itemView);
            tag = (TextView) itemView.findViewById(R.id.txtWord);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (type==0){
            return keyDAO.getKeywords().size();
        }else{
            return genreDao.size();
        }


    }


}
