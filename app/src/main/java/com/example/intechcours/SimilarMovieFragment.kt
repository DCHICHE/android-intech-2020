package com.example.intechcours

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class SimilarMovieFragment : Fragment(),AdapterMovie.ClickMovieListener {

    private lateinit var similarsMovieList : ArrayList<Movie>;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        similarsMovieList = arguments?.getSerializable("SimilarMovie") as ArrayList<Movie>;
        return inflater.inflate(R.layout.similar_movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterMovie(similarsMovieList, this);
        val recyclerView = view.findViewById<View>(R.id.movie_similar_list) as RecyclerView
        recyclerView.setLayoutManager(LinearLayoutManager(this.context));
        recyclerView.setAdapter(adapter);

    }

    override fun onMovieClick(position: Int) {
        val intent = Intent(this.context, MovieDetailsActivity::class.java).apply {
            putExtra("Movie", similarsMovieList[position])
        }
        startActivity(intent)
    }
}