package com.example.intechcours

import android.R.attr.data
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailMovieActivityFragment : Fragment() {

    private lateinit var actualMovie: Movie;
    private lateinit var prefs: SharedPreferences;
    private var isLiked: Boolean = false;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actualMovie = arguments?.getSerializable("Movie") as Movie;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.description_text)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("4", name, importance).apply {
                description = descriptionText
            }


            // Register the channel with the system
            val notificationManager: NotificationManager = context?.applicationContext?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        this.prefs =
            view.context.getSharedPreferences("likeMovies", AppCompatActivity.MODE_PRIVATE);

        val likeMovies = prefs.getStringSet("likeMovies", HashSet<String>())

        val poster = view.findViewById<ImageView>(R.id.imageViewDetail)
        val overview = view.findViewById<TextView>(R.id.overviewDetail)
        val movieTitle = view.findViewById<TextView>(R.id.movieTitle)
        val likeButton = view.findViewById<ImageView>(R.id.like_button)

        isLiked = likeMovies?.contains(actualMovie.id.toString())!!;

        likeButton.setOnClickListener {
            this.onClick(likeMovies as HashSet<String>)
        }

        if (isLiked!!) {
            likeButton.setImageResource(R.drawable.like)
        } else likeButton.setImageResource(R.drawable.notlike)

        try {
            val url = "https://image.tmdb.org/t/p/w500/" + actualMovie.poster_path;
            Picasso.get().load(url).into(poster)
        } catch (e: Exception) {
        }

        overview?.text = actualMovie.overview;
        movieTitle?.text = actualMovie.title;
    }

    fun onClick(likeMovies: HashSet<String>) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        val likeButton = view?.findViewById<ImageView>(R.id.like_button)

        var likePhrase = "";

        if (isLiked!!) {
            likeMovies?.remove(actualMovie.id.toString())
            likePhrase = resources.getString(R.string.unlike_button);
            likeButton?.setImageResource(R.drawable.notlike)

        } else {
            likeMovies.add(actualMovie.id.toString())
            likePhrase = resources.getString(R.string.like_button);
            likeButton?.setImageResource(R.drawable.like)
        }
        isLiked = !isLiked;
        prefsEditor.putStringSet("likeMovies", likeMovies)
        prefsEditor.commit()

        Toast.makeText(
            activity, (likePhrase + actualMovie.title),
            Toast.LENGTH_LONG
        ).show()
        this.sendNotification()

    }

    fun sendNotification() {

        var titleContent = "Your Movie List"
        var messageContent =
        if(this.isLiked){
            resources.getString(R.string.like_button)  + actualMovie.title;
        }else{
            resources.getString(R.string.unlike_button)  + actualMovie.title;
        }

        var builder =
            NotificationCompat.Builder(this.requireContext(), "4")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(titleContent)
                .setContentText(messageContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        with(NotificationManagerCompat.from(this.requireContext())) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
    }


}