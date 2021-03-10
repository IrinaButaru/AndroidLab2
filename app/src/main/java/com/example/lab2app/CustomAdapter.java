package com.example.lab2app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MovieViewHolder> {

    private List<MovieModel> localDataSet;
    public static OnItemClickListener itemClickListener;

    public CustomAdapter(List<MovieModel> localDataSet, OnItemClickListener onItemClickListener) {
        this.localDataSet = localDataSet;
        itemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView duration;
        private final ImageView movieImage;

        private final ConstraintLayout layout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            duration = itemView.findViewById(R.id.tv_duration);
            movieImage = itemView.findViewById(R.id.iv_picture);

            layout = itemView.findViewById(R.id.container);

        }

        public void bind(MovieModel item) {
            title.setText(item.getTitle());
            duration.setText(item.getDuration());

            movieImage.setImageDrawable(ContextCompat.getDrawable(movieImage.getContext(), item.getImageId()));

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }
}
