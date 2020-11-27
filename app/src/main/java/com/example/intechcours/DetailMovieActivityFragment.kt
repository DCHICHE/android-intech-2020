package com.example.intechcours

import android.R.attr.data
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class DetailMovieActivityFragment : Fragment() {

    private lateinit var actualMovie : Movie;
    private lateinit var prefs: SharedPreferences;
    private var isLiked: Boolean = false;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actualMovie = arguments?.getSerializable("Movie") as Movie;
        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        this.prefs = view.context.getSharedPreferences("likeMovies", AppCompatActivity.MODE_PRIVATE);

        val likeMovies =  prefs.getStringSet("likeMovies", HashSet<String>())

        val poster = view.findViewById<ImageView>(R.id.imageViewDetail)
        val genres = view.findViewById<TextView>(R.id.genre)
        val overview = view.findViewById<TextView>(R.id.overviewDetail)
        val movieTitle = view.findViewById<TextView>(R.id.movieTitle)
        val likeButton = view.findViewById<ImageView>(R.id.like_button)

        isLiked = likeMovies?.contains(actualMovie.id.toString())!!;

        likeButton.setOnClickListener {
            this.onClick(likeMovies as HashSet<String>)
        }

        if(isLiked!!){
            likeButton.setImageResource(R.drawable.like)
        }else likeButton.setImageResource(R.drawable.notlike)

        try {
            val url = "https://image.tmdb.org/t/p/w500/" + actualMovie.poster_path;
            Picasso.get().load(url).into(poster)
        } catch (e: Exception) {
        }

        genres?.text = actualMovie.genre_ids.joinToString(",")
        overview?.text = actualMovie.overview;
        movieTitle?.text = actualMovie.title;
    }

    fun onClick(likeMovies: HashSet<String>) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        val likeButton = view?.findViewById<ImageView>(R.id.like_button)

        var likePhrase ="";

        if(isLiked!!){
            likeMovies?.remove(actualMovie.id.toString())
            likePhrase = resources.getString(R.string.unlike_button);
            likeButton?.setImageResource(R.drawable.notlike)

        }else{
            likeMovies.add(actualMovie.id.toString())
            likePhrase = resources.getString(R.string.like_button);
            likeButton?.setImageResource(R.drawable.like)
        }
        isLiked = !isLiked;
        prefsEditor.putStringSet("likeMovies", likeMovies)
        prefsEditor.commit()

        Toast.makeText(
            activity,(likePhrase + actualMovie.title),
            Toast.LENGTH_LONG
        ).show()

    }



}