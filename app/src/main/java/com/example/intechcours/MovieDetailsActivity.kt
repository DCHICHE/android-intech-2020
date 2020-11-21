package com.example.intechcours

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_movie_activity)

        val movieSerializable = intent.getSerializableExtra("Movie");
        val movie = movieSerializable as Movie;
        val bundle = Bundle();
        bundle.putSerializable("Movie", movieSerializable);

        val poster = findViewById<ImageView>(R.id.imageViewDetail);
        val genres = findViewById<TextView>(R.id.genre);
        val overview = findViewById<TextView>(R.id.overviewDetail);
        val movieTitle = findViewById<TextView>(R.id.movieTitle);

        try {
            val url = "https://image.tmdb.org/t/p/w500" + movie.poster_path;
            Picasso.get().load(url).into(poster)
        } catch (e: Exception) {
        }

        genres?.text = movie.genre_ids.joinToString { "," };
        overview?.text  = movie.overview
        movieTitle?.text = movie.title;

        val fragmentManager = supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction();

        val mainFragment = DetailMovieActivityFragment();
        mainFragment.arguments = bundle;

        fragmentTransaction.add(R.id.detailMovieFragment, DetailMovieActivityFragment());
        fragmentTransaction.commit();


    }
}