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


//
//        btn_Camera.setOnClickListener {
//            val intent = Intent(this, CameraActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val btn_camera = container?.findViewById<Button>(R.id.camera_activity_btn);
        btn_camera?.setOnClickListener {
            Log.i("debug-test", "go to camera");

            val intent = Intent(this@BottomBar.context, CameraActivity::class.java);
            startActivity(intent);
        }

        val btn_main = container?.findViewById<Button>(R.id.main_activity_btn);
        btn_main?.setOnClickListener {
            Log.i("debug-test", "go to main");
            val intent = Intent(this@BottomBar.context, MainActivity::class.java);
            startActivity(intent);
        }

        return inflater.inflate(R.layout.fragment_bottom_bar, container, false)
    }

}