package com.example.intechcours

import android.app.FragmentManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(), AdapterMovie.ClickMovieListener {

    private val disposable = CompositeDisposable();
    val listMovie = arrayListOf<Movie>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_click_me = findViewById<Button>(R.id.button)

        btn_click_me.setOnClickListener {
          this.OnSearch()
        }
    }

    fun OnSearch(){
        val editText: EditText = findViewById(R.id.searchMovie);

        disposable.add(ApiService.searchMovie(editText.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe ({
                listMovie.clear();
                listMovie.addAll(it.results);
                val adapter = AdapterMovie(listMovie,this);
                val recyclerView = findViewById<View>(R.id.movieList) as RecyclerView
                recyclerView.setLayoutManager( LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
//                recyclerView.getLayoutManager()?.setMeasurementCacheEnabled(false);

            },Throwable::printStackTrace)
        )
    }

    override fun onMovieClick(position: Int) {
        Log.i("titleMovie",listMovie[position].title)
    }
}