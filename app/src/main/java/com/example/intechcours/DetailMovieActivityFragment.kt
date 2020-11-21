package com.example.intechcours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailMovieActivityFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//
//        val poster = container?.findViewById<ImageView>(R.id.imageViewDetail);
//        val genres = container?.findViewById<TextView>(R.id.genre);
//        val overview = container?.findViewById<TextView>(R.id.overviewDetail);
//        val movieTitle = container?.findViewById<TextView>(R.id.movieTitle);
//
//        try {
//            val url = "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg";
//            Picasso.get().load(url).into(poster)
//        } catch (e: Exception) {
//        }
//
//        genres?.text = "Aventure, Action, Super-Héros"
//        overview?.text = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, " +
//                "Thanos. With the help of remaining allies, the Avengers must assemble once more in " +
//                "order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.";
//        movieTitle?.text = "Avengers : EndGame"

        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }


}