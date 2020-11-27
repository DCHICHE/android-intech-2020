package com.example.intechcours

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_movie_activity)

        val movieSerializable = intent.getSerializableExtra("Movie");
        val movie = movieSerializable as Movie;
        val te = Bundle();
        te.putSerializable("Movie", movieSerializable);

        val fragmentManager = supportFragmentManager;
        val mainFragment = DetailMovieActivityFragment()
        mainFragment.arguments = te;

        fragmentManager
            .beginTransaction()
            .add(R.id.detailMovieFragment, mainFragment)
            .commit();


//        val poster = findViewById<ImageView>(R.id.imageViewDetail);
//        val genres = findViewById<TextView>(R.id.genre);
//        val overview = findViewById<TextView>(R.id.overviewDetail);
//        val movieTitle = findViewById<TextView>(R.id.movieTitle);
//
//        try {
//            val url = "https://image.tmdb.org/t/p/w500" + movie.poster_path;
//            Picasso.get().load(url).into(poster)
//        } catch (e: Exception) {
//        }
//
//        genres?.text = movie.genre_ids.joinToString { "," };
//        overview?.text  = movie.overview
//        movieTitle?.text = movie.title;


    }

}