package com.example.intechcours

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class LanguageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.language_change_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val frenchFlag = view.findViewById<ImageView>(R.id.frenchFlag);
        val englishFlag = view.findViewById<ImageView>(R.id.englishFlag);

        frenchFlag?.setOnClickListener {
            val myLocale = Locale("fr")
            val res: Resources = resources
            val dm: DisplayMetrics = res.displayMetrics
            val conf: Configuration = res.configuration
            conf.locale = myLocale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(this.context, MainActivity::class.java);
            this.activity?.finish();
            startActivity(refresh);
        }

        englishFlag?.setOnClickListener {
            val myLocale = Locale("en")
            val res: Resources = resources
            val dm: DisplayMetrics = res.displayMetrics
            val conf: Configuration = res.configuration
            conf.locale = myLocale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(this.context, MainActivity::class.java);
            this.activity?.finish();
            startActivity(refresh);
        }

    }



}