package com.example.intechcours

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_movie_activity)

        val movie = getIntent().getSerializableExtra("Movie") as Movie;
        val bundle =  Bundle();
        bundle.putString("edttext", "From Activity")

        val fragmentManager = supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction();

        val mainFragment = MainFragment();
        mainFragment.arguments.

        fragmentTransaction.add(R.id.detailMovieFragment, MainFragment());
        fragmentTransaction.commit();

        Log.i("MovieIntent", movie.title)

    }
}