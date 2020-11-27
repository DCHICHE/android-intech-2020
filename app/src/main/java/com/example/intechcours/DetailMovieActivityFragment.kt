package com.example.intechcours

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class DetailMovieActivityFragment : Fragment() {

    private lateinit var actualMovie : Movie;

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

        val poster = view.findViewById<ImageView>(R.id.imageViewDetail)
        val genres = view.findViewById<TextView>(R.id.genre)
        val overview = view.findViewById<TextView>(R.id.overviewDetail)
        val movieTitle = view.findViewById<TextView>(R.id.movieTitle)

        try {
            val url = "https://image.tmdb.org/t/p/w500/" + actualMovie.poster_path;
            Picasso.get().load(url).into(poster)
        } catch (e: Exception) {
        }

        genres?.text = actualMovie.genre_ids.joinToString(",")
        overview?.text = actualMovie.overview;
        movieTitle?.text = actualMovie.title;
    }



}