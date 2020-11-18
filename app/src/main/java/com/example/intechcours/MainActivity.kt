package com.example.intechcours

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable();
    val listMovie = arrayListOf<Movie>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_click_me = findViewById<Button>(R.id.button)
        val textView: TextView = findViewById(R.id.textView)

        textView.setText("ffff");

        btn_click_me.setOnClickListener {
          this.OnSearch()
        }
    }

    fun OnSearch(){
        val textView: TextView = findViewById(R.id.textView);
        val editText: EditText = findViewById(R.id.searchMovie);
        val myImageView: ImageView = findViewById(R.id.imageView);

        disposable.add(ApiService.searchMovie(editText.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe ({
                listMovie.clear();
                listMovie.addAll(it.results)
                runOnUiThread {
                     try {
                         val url = "https://image.tmdb.org/t/p/w200" + listMovie[0].poster_path;
                         Log.i("e",url)
                         Picasso.get().load(url).into(myImageView)
                    } catch (e: Exception) {
                    }
                    textView.text = listMovie[0].title;
                }
            },Throwable::printStackTrace)
        )
    }
}