package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WatchMoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private Button btnWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movies);

        recyclerView = findViewById(R.id.recyclerView);
        btnWatch = findViewById(R.id.btnWatch);

        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Altera a cor do card para verde
                movieAdapter.watchCurrentMovie();
            }
        });
    }

    // ... Rest of your code

    private class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
        private final MyMovie[] movies = {
                new MyMovie("Movie 1", false),
                new MyMovie("Movie 2", false),
                // Add more movies here
        };

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflate the layout for a single item and return a new instance of your custom ViewHolder
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
            return new MovieViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
            final MyMovie movie = movies[position];
            // Configure the elements of the layout based on the movie data
            holder.movieTitle.setText(movie.title);

            if (movie.watched) {
                holder.itemView.setBackgroundColor(Color.GREEN);
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE);
            }

            holder.imgFuria.setImageResource(R.drawable.furia_img);

            holder.btnWatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Change the card's color to green and save the user's preference
                    movie.watched = true;
                    holder.itemView.setBackgroundColor(Color.GREEN);
                    saveMoviePreference(holder.getAdapterPosition(), true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return movies.length;
        }

        public void watchCurrentMovie() {
            // Function to watch the current movie
            int currentPosition = recyclerView.getChildLayoutPosition(recyclerView.getFocusedChild());
            if (currentPosition >= 0 && currentPosition < movies.length) {
                MyMovie currentMovie = movies[currentPosition];
                if (!currentMovie.watched) {
                    currentMovie.watched = true;
                    notifyItemChanged(currentPosition);
                    saveMoviePreference(currentPosition, true);
                }
            }
        }
    }

    private void saveMoviePreference(int movieIndex, boolean isWatched) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String movieKey = "movie" + movieIndex;
        editor.putBoolean(movieKey, isWatched);
        editor.apply();
    }
}

class MyMovie {
    String title;
    boolean watched;

    MyMovie(String title, boolean watched) {
        this.title = title;
        this.watched = watched;
    }
}
