package com.example.intechcours

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(), AdapterMovie.ClickMovieListener {

    private var prefs: SharedPreferences? = getPreferences(MODE_PRIVATE)
    private val disposable = CompositeDisposable();
    val history = arrayListOf<Movie>();
    val listMovie = arrayListOf<Movie>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val fragmentManager = supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.bottom_bar, BottomBar());
        fragmentTransaction.commit();

        val btn_click_me = findViewById<Button>(R.id.button)
        btn_click_me.setOnClickListener {
          this.OnSearch()
        }

        val gson = Gson()
        val json: String? = prefs?.getString("history", "")
        if (json != null && json.isNotEmpty()) {
            history.addAll(gson.fromJson(json, arrayListOf<Movie>()::class.java));
        }
        openHistory();
    }

    fun OnSearch(){
        val editText: EditText = findViewById(R.id.searchMovie);
        if (editText.text.toString().isEmpty()) {
            openHistory();
        } else {
            disposable.add(ApiService.searchMovie(editText.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.i("", "");
                }
                .subscribe({
                    listMovie.clear();
                    listMovie.addAll(it.results);
                    val adapter = AdapterMovie(listMovie, this);
                    val recyclerView = findViewById<View>(R.id.movieList) as RecyclerView
                    recyclerView.setLayoutManager(LinearLayoutManager(this));
                    recyclerView.setAdapter(adapter);
//                recyclerView.getLayoutManager()?.setMeasurementCacheEnabled(false);
                }, Throwable::printStackTrace)
            );
        }
    }

    fun openHistory() {
        val adapter = AdapterMovie(history, this);
        val recyclerView = findViewById<View>(R.id.movieList) as RecyclerView
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    fun saveHistory() {
        val prefsEditor: SharedPreferences.Editor = prefs!!.edit()
        val gson = Gson()
        val json = gson.toJson(history)
        prefsEditor.putString("history", json)
        prefsEditor.commit()
    }

    override fun onMovieClick(position: Int) {
        history.add(0, listMovie[position]);
        saveHistory()
        Log.i("titleMovie", listMovie[position].title)
    }
//            },Throwable::printStackTrace)
//        )
//
//
//        btn_click_me.setOnClickListener {
//            AlertDialog.Builder(this)
//                .setCancelable(false)
//                .setTitle("Confirmation")
//                .setMessage("Picture ?")
//                .setPositiveButton("Yes") { _: DialogInterface, i: Int ->
//                    val intent = Intent(applicationContext, SecondActivity::class.java)
//                    startActivity(intent)
//                }
//                .setNegativeButton("No", null)
//                .create()
//                .show()
//
//        }
//
//        val bottonBar = findViewById<BottomNavigationView>(R.id.bottom_navigation);
//
//        bottonBar.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.page_1 -> {
//                    Log.i("0", "First");
//                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    try {
//                        startActivityForResult(takePictureIntent, 1)
//                    } catch (e: ActivityNotFoundException) {
//                        // display error state to the user
//                    }
//                    true
//                }
//                R.id.page_2 -> {
//                    Log.i("0", "Second")
//                    true
//                }
//                else -> false
//            }
//        }
//    }
}