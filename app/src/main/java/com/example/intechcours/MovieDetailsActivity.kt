package com.example.intechcours

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class MovieDetailsActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable();
    private val likeMovie = arrayListOf<Int>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_movie_activity)

        val movieSerializable = intent.getSerializableExtra("Movie");
        val movie = movieSerializable as Movie;
        val movieBundle = Bundle();
        movieBundle.putSerializable("Movie", movieSerializable);

        disposable.add(ApiService.getSimilarMovie(movie.id,resources.configuration.locale.toLanguageTag())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe({
                movieBundle.putSerializable("SimilarMovie",it.results)
            }, Throwable::printStackTrace))

        val fragmentManager = supportFragmentManager;
        val detaiMovieFragment = DetailMovieActivityFragment()
        val similarMovieFragment = SimilarMovieFragment()
        detaiMovieFragment.arguments = movieBundle;
        similarMovieFragment.arguments = movieBundle;

        val fragmentTransact = fragmentManager.beginTransaction()

        fragmentTransact.add(R.id.detailMovieFragment, detaiMovieFragment);
        fragmentTransact.add(R.id.bottom_bar, BottomBar());
        fragmentTransact.commit()


        val navigationDetailsMovie = findViewById<BottomNavigationView>(R.id.navigation_details_movie);
        navigationDetailsMovie.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.detailsMovie -> {
                    fragmentManager.beginTransaction().replace(R.id.detailMovieFragment, detaiMovieFragment).commit()
                    true
                }
                R.id.similarMovie -> {
                    fragmentManager.beginTransaction().replace(R.id.detailMovieFragment, similarMovieFragment).commit()
                    true
                }
                else -> false
            }
        }

    }


}