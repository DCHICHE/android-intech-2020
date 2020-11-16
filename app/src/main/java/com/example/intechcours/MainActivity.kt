package com.example.intechcours

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_click_me = findViewById<Button>(R.id.button)
        val textView: TextView = findViewById(R.id.textView)

        textView.setText("ffff");

        disposable.add(ApiService.searchMovie("pirate")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe ({
                runOnUiThread {
                    textView.text = it.results[0].title;
                }
            },Throwable::printStackTrace)
        )


        btn_click_me.setOnClickListener {
            AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Confirmation")
                .setMessage("Picture ?")
                .setPositiveButton("Yes") { _: DialogInterface, i: Int ->
                    val intent = Intent(applicationContext, SecondActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No", null)
                .create()
                .show()

        }

        val bottonBar = findViewById<BottomNavigationView>(R.id.bottom_navigation);

        bottonBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    Log.i("0", "First");
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startActivityForResult(takePictureIntent, 1)
                    } catch (e: ActivityNotFoundException) {
                        // display error state to the user
                    }
                    true
                }
                R.id.page_2 -> {
                    Log.i("0", "Second")
                    true
                }
                else -> false
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap;
            val imageView = findViewById<ImageView>(R.id.thumbTake)
            imageView.setImageBitmap(imageBitmap)
        }
    }
}