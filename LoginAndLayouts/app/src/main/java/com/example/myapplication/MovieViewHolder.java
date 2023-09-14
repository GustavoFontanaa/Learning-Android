package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView movieTitle;
    Button btnWatch;

    ImageView imgFuria;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieTitle = itemView.findViewById(R.id.movieTitle);
        btnWatch = itemView.findViewById(R.id.btnWatch);
        imgFuria = itemView.findViewById(R.id.movieImage);
    }
}

