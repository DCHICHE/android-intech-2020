package com.example.intechcours

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainActivity : AppCompatActivity(), AdapterMovie.ClickMovieListener {

    private val disposable = CompositeDisposable();
    val history = arrayListOf<Movie>();
    val listMovie = arrayListOf<Movie>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val fragmentManager = supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.bottom_bar, BottomBar());
        fragmentTransaction.add(R.id.languageChange, LanguageFragment());
        fragmentTransaction.commit();

        val btn_click_me = findViewById<Button>(R.id.button)
        btn_click_me.setOnClickListener {
            this.OnSearch()
        }
        openHistory();
    }

    fun OnSearch() {
        val editText: EditText = findViewById(R.id.searchMovie);
        if (editText.text.toString().isEmpty()) {
            openHistory();
        } else {

            disposable.add(ApiService.searchMovie(editText.text.toString(), resources.configuration.locale.toLanguageTag())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                }
                .subscribe({
                    listMovie.clear();
                    listMovie.addAll(it.results);
                    val adapter = AdapterMovie(listMovie, this);
                    val recyclerView = findViewById<View>(R.id.movieList) as RecyclerView
                    recyclerView.setLayoutManager(LinearLayoutManager(this));
                    recyclerView.setAdapter(adapter);
                }, Throwable::printStackTrace)
            )
        }

    }

    fun openHistory() {
        val adapter = AdapterMovie(history, this);
        val recyclerView = findViewById<View>(R.id.movieList) as RecyclerView
    }

    override fun onMovieClick(position: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra("Movie", listMovie[position])
        }
        startActivity(intent)
    }

}