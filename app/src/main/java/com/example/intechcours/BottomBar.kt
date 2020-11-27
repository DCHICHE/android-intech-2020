package com.example.intechcours

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class BottomBar : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_bottom_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_camera = view.findViewById<Button>(R.id.camera_activity_btn);
        btn_camera?.setOnClickListener {
            Log.i("debug-test", "go to camera");

            val intent = Intent(this@BottomBar.context, CameraActivity::class.java);
            startActivity(intent);
        }

        val btn_main = view.findViewById<Button>(R.id.main_activity_btn);
        btn_main?.setOnClickListener {
            Log.i("debug-test", "go to main");
            val intent = Intent(this@BottomBar.context, MainActivity::class.java);
            startActivity(intent);
        }
    }

}